package br.edu.ifsp.spo.bulls.feed.api.service;

import br.edu.ifsp.spo.bulls.common.api.dto.GroupInviteTO;
import br.edu.ifsp.spo.bulls.common.api.dto.UserTO;
import br.edu.ifsp.spo.bulls.common.api.enums.Role;
import br.edu.ifsp.spo.bulls.common.api.exception.ResourceUnauthorizedException;
import br.edu.ifsp.spo.bulls.feed.api.bean.GroupMemberBeanUtil;
import br.edu.ifsp.spo.bulls.feed.api.domain.GroupInvite;
import br.edu.ifsp.spo.bulls.feed.api.domain.GroupMemberId;
import br.edu.ifsp.spo.bulls.feed.api.domain.GroupMembers;
import br.edu.ifsp.spo.bulls.feed.api.domain.GroupRead;
import br.edu.ifsp.spo.bulls.feed.api.dto.GroupMemberTO;
import br.edu.ifsp.spo.bulls.feed.api.enums.MemberStatus;
import br.edu.ifsp.spo.bulls.feed.api.feign.UserCommonFeign;
import br.edu.ifsp.spo.bulls.feed.api.repository.GroupInviteRepository;
import br.edu.ifsp.spo.bulls.feed.api.repository.GroupMemberRepository;
import br.edu.ifsp.spo.bulls.feed.api.repository.GroupRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(loader= AnnotationConfigContextLoader.class, classes = {GroupMemberService.class, GroupMemberBeanUtil.class})
public class GroupReadMemberServiceTest {

    @Autowired
    private GroupMemberService service;

    @MockBean
    private GroupRepository mockGroupRepository;

    @MockBean
    private GroupMemberRepository mockGroupMemberRepository;

    @MockBean
    private UserCommonFeign feign;

    @MockBean
    private GroupInviteRepository inviteRepository;


    private GroupMembers groupMembers;
    private List<GroupMembers> groupMembersList;
    private GroupRead groupRead;
    private List<GroupRead> groupReadList;
    private GroupMemberTO groupMemberTO;
    private UserTO user = new UserTO();

    @BeforeEach
    void setUp() {
        groupRead = new GroupRead();
        groupRead.setId(UUID.randomUUID());

        GroupMemberId id = new GroupMemberId();
        id.setGroupRead(groupRead);
        id.setUser(UUID.randomUUID());

        user.setId(id.getUser());

        groupMembers = new GroupMembers();
        groupMembers.setRole(Role.admin);
        groupMembers.setDate(LocalDateTime.now());
        groupMembers.setId(id);

        groupMembersList = new ArrayList<>();
        groupMembersList.add(groupMembers);

        groupReadList = new ArrayList<>();
        groupReadList.add(groupRead);

        groupMemberTO = new GroupMemberTO();
        groupMemberTO.setUserId(groupMembers.getId().getUser());
        groupMemberTO.setGroupId(groupMembers.getId().getGroupRead().getId());
        groupMemberTO.setDate(groupMembers.getDate());
        groupMemberTO.setRole(groupMembers.getRole());
    }

    @Test
    void putMember() {
        Mockito.when(mockGroupMemberRepository.save(groupMembers)).thenReturn(groupMembers);
        Mockito.when(feign.getUserById(groupMembers.getId().getUser())).thenReturn(new UserTO());
        Mockito.when(mockGroupRepository.findById(groupMembers.getId().getGroupRead().getId())).thenReturn(Optional.ofNullable(groupRead));
        Mockito.when(feign.getUserById(groupMembers.getId().getUser())).thenReturn(new UserTO());
        Mockito.when(feign.getUserInfo("token")).thenReturn(new UserTO());

        service.putMember(null, groupMemberTO);
        groupMembers.setStatus(MemberStatus.accepted);

        Mockito.verify(mockGroupMemberRepository).save(groupMembers);
    }

    @Test
    void exitMember() {
        Mockito.doNothing().when(mockGroupMemberRepository).deleteById(groupMembers.getId());
        Mockito.when(mockGroupRepository.findById(groupMembers.getId().getGroupRead().getId())).thenReturn(Optional.ofNullable(groupRead));
        UserTO user = new UserTO();
        user.setId(groupMemberTO.getUserId());
        Mockito.when(feign.getUserInfo("token")).thenReturn(user);

        service.exitMember("token", groupMemberTO);

        Mockito.verify(mockGroupMemberRepository).deleteById(groupMembers.getId());
    }

    @Test
    void shouldGetGroupsByUser() {
        Mockito.when(mockGroupMemberRepository.findByIdUser(groupMembers.getId().getUser())).thenReturn(groupReadList);

        service.getGroupByUser(groupMembers.getId().getUser());

        Mockito.verify(mockGroupMemberRepository).findByIdUser(groupMembers.getId().getUser());
    }

    @Test
    void shouldGetMemberOfAGroup() {
        Mockito.when(mockGroupMemberRepository.findByIdGroupRead(groupMembers.getId().getGroupRead())).thenReturn(groupMembersList);
        Mockito.when(mockGroupRepository.findById(groupMembers.getId().getGroupRead().getId())).thenReturn(Optional.of(groupRead));
        service.getGroupMembers(groupMembers.getId().getGroupRead().getId());

        Mockito.verify(mockGroupMemberRepository).findByIdGroupRead(groupMembers.getId().getGroupRead());
    }

    @Test
    void shouldInvite() {
        GroupInvite invite = new GroupInvite();
        invite.setId(UUID.randomUUID());
        invite.setGroup(groupMembers.getId().getGroupRead());
        invite.setUserId(groupMembers.getId().getUser());

        GroupInviteTO inviteTO = new GroupInviteTO();
        inviteTO.setGroup(groupMembers.getId().getGroupRead());
        inviteTO.setUserId(groupMembers.getId().getUser());

        Mockito.when(mockGroupMemberRepository.findMemberByUserId(invite.getUserId(), invite.getGroup().getId())).thenReturn(groupMembers);
        Mockito.when(mockGroupRepository.findById(groupMembers.getId().getGroupRead().getId())).thenReturn(Optional.of(groupRead));
        Mockito.when(feign.getUserInfo("token")).thenReturn(user);
        Mockito.when(inviteRepository.save(invite)).thenReturn(invite);
        Assertions.assertThrows(ResourceUnauthorizedException.class, () -> service.invite("token", inviteTO));
    }

    @Test
    void shouldGetInvites() {
        Mockito.when(inviteRepository.findByUserId(groupMembers.getId().getUser())).thenReturn(new ArrayList<>());
        Mockito.when(feign.getUserInfo("token")).thenReturn(user);
        service.getInvites("token", groupMembers.getId().getUser());
        Mockito.verify(inviteRepository).findByUserId(user.getId());
    }
}