package org.jiyoung.kikihi.platform.adapter.out.oauth2;

import org.jiyoung.kikihi.platform.adapter.out.jpa.user.User;
import org.jiyoung.kikihi.platform.adapter.out.jpa.user.MemberRepository;
import org.jiyoung.kikihi.common.security.response.GoogleResponse;
import org.jiyoung.kikihi.common.security.response.NaverResponse;
import org.jiyoung.kikihi.common.security.response.OAuth2Response;
import org.jiyoung.kikihi.platform.adapter.in.web.dto.OAuthUserDTO;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


// 유저 정보를 획득하기 위한 클래스
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberRepository memberRepository;

    public CustomOAuth2UserService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //DefaultOAuth2UserService에서 OAuth2User를 상속받은 객체를 반환
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        String registrationId=userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response response=null;

        if(registrationId.equals("google")){
            response=new GoogleResponse(oAuth2User.getAttributes());
            System.out.println("response: "+response);
        }
        else if(registrationId.equals("naver")){
            response=new NaverResponse(oAuth2User.getAttributes());
            System.out.println("response: "+response);}
      else{
          return null;
        }

      //리소스(네이버,구글) 서버에서 발급받은 정보(id)로 사용자를 특정할 아이디값을 만듦 (ex: naver1234)
      String userName=response.getProvider()+" "+response.getProviderId();
      System.out.println("Provider: " + response.getProvider());
      System.out.println("ProviderId: " + response.getProviderId());

      User existData=memberRepository.findByName(userName);

      if(existData==null){
          User user = User.builder()
                  .userName(userName)
                  .email(response.getEmail())
                .name(response.getName())
                .build();
          System.out.println("member: "+ user);
          memberRepository.save(user);

          OAuthUserDTO oAuthUserDTO = OAuthUserDTO.builder()
              .username(userName)
              .name(response.getName())
              .build();
            return new CustomOAuth2User(oAuthUserDTO);
      }else {
          // 업데이트
          existData.setEmail(response.getEmail());
          existData.setName(response.getName());
          System.out.println("existData: "+existData);

          memberRepository.save(existData);

          OAuthUserDTO oAuthUserDTO = OAuthUserDTO.builder()
              .username(existData.getUsername())
              .name(response.getName())
              .build();
            return new CustomOAuth2User(oAuthUserDTO);
      }
    }
}
