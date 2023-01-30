package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.dto.JoinForm;
import io.k2c1.hereyougo.dto.MemberUpdateForm;
import io.k2c1.hereyougo.dto.MyPageForm;
import io.k2c1.hereyougo.repository.PostRepository;
import io.k2c1.hereyougo.service.MemberService;
import io.k2c1.hereyougo.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/members")
@Controller
public class MemberController {

    private MemberService memberService;
    private PostService postService;

    public MemberController(MemberService memberService, PostService postService){
        this.memberService = memberService;
        this.postService = postService;
    }

    /***
     * 회원가입
     */
    @PostMapping
    public String join(JoinForm joinForm){
        memberService.join(joinForm);

        return "redirect:login";
    }
    /***
     * 회원정보 수정 페이지로 이동
     * TODO
     * 현재 임의로 memberId를 지정하여 멤버 상세정보를 조회하지만
     * 세션 적용 후 세션을 통해 memberId나 이메일을 가져와서 상세정보 조회하는걸로 변경 필요
     */
    @GetMapping("/edit")
    public String updateMemberForm(Model model){
      
        // 현재 회원 정보 출력
        Member member = memberService.findMember(1L);
        
        MemberUpdateForm updateForm = new MemberUpdateForm();
        updateForm.setId(member.getId());
        updateForm.setEmail(member.getEmail());
        updateForm.setNickname(member.getNickname());
        updateForm.setBusinessType(member.getBusinessType());
        
        model.addAttribute("updateForm", updateForm);
        return "member/updateMemberForm";
    }

    /***
     * 회원정보 수정
     */
    @PostMapping("/edit")
    public String updateMemberInfo(MemberUpdateForm updateForm){
        memberService.updateMemberInfo(updateForm);
        return "redirect:/";
    }


    /***
     * 회원정보 조회
     */
    @GetMapping("/{memberId}")
    public String mypage(@PathVariable("memberId") Long memberId, Model model){
        MyPageForm memberInfo = memberService.mypage(memberId);
        model.addAttribute("mypage", memberInfo);
        return "member/myPage";
    }

    /***
     * 회원탈퇴
     * @DeleteMapping은 a태그를 url로 접근하는걸 지원안해서 @GetMapping으로 변경
     * input hidden을 통해 보내는 방법이 있지만 잘 사용안한다고 함
     * cascade 사용하는 것보다 따로 remove 호출하는게 낫다고 하여 따로 호출
     * www.inflearn.com/questions/141700/
     */
//    @DeleteMapping("/delete/{memberId}")
    @GetMapping("/delete/{memberId}")
    public String deleteMember(@PathVariable("memberId") Long memberId){
        postService.deleteByWriter(memberId);
        memberService.deleteMember(memberService.findMember(memberId));

        return "redirect:/";
    }
}
