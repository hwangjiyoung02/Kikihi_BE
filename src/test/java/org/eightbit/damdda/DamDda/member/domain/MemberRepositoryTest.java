//package org.eightbit.damdda.DamDda.member.domain;
//
//import org.eightbit.damdda.DamDda.member.repository.MemberRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ActiveProfiles("test")//✌resource의 설정을 따르도록함
//@DataJpaTest//✌반드시 h2의존성을 추가해야사용할수 있음
//class MemberRepositoryTest {
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    @DisplayName("회원 저장 테스트")
//    void saveMemberTest() {
//        // given
//        Member member = Member.createMember(
//                "test@example.com",
//                "password123",
//                "Test User",
//                LoginProvider.GOOGLE,
//                "socialId123",
//                "https://example.com/image.jpg"
//        );
//
//        // when
//        Member savedMember = memberRepository.save(member);
//
//        // then
//        assertThat(savedMember.getId()).isNotNull();
//        assertThat(savedMember.getEmail()).isEqualTo("test@example.com");
//        assertThat(savedMember.getName()).isEqualTo("Test User");
//    }
//
//    @Test
//    @DisplayName("회원 조회 테스트")
//    void findMemberByEmailTest() {
//        // given
//        Member member = Member.createMember(
//                "test@example.com",
//                "password123",
//                "Test User",
//                LoginProvider.GOOGLE,
//                "socialId123",
//                "https://example.com/image.jpg"
//        );
//        memberRepository.save(member);
//
//        // when
//        Member foundMember = memberRepository.findByEmail("test@example.com").orElse(null);
//
//        // then
//        assertThat(foundMember).isNotNull();
//        assertThat(foundMember.getName()).isEqualTo("Test User");
//    }
//
//    @Test
//    @DisplayName("회원 삭제 테스트")
//    void deleteMemberTest() {
//        // given
//        Member member = Member.createMember(
//                "test@example.com",
//                "password123",
//                "Test User",
//                LoginProvider.GOOGLE,
//                "socialId123",
//                "https://example.com/image.jpg"
//        );
//        Member savedMember = memberRepository.save(member);
//
//        // when
//        memberRepository.delete(savedMember);
//
//        // then
//        assertThat(memberRepository.findByEmail("test@example.com")).isEmpty();
//    }
//}
