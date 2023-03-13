package io.k2c1.hereyougo.controller;

import io.k2c1.hereyougo.constant.SessionConst;
import io.k2c1.hereyougo.domain.Member;
import io.k2c1.hereyougo.dto.JoinForm;
import io.k2c1.hereyougo.dto.MemberUpdateForm;
import io.k2c1.hereyougo.dto.MyPageForm;
import io.k2c1.hereyougo.service.CategoryService;
import io.k2c1.hereyougo.service.EmailService;
import io.k2c1.hereyougo.service.MemberService;
import io.k2c1.hereyougo.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;
    private final CategoryService categoryService;
    private final EmailService emailService;

    private Map<String, String> authCodeRepo = new HashMap<>(); // <요청email, 전송된 authCode>

    /***
     * 회원가입
     */
    @GetMapping("/join")
    public String joinForm(@ModelAttribute("joinForm") JoinForm joinForm, Model model) {
//        memberService.join(joinForm);
        model.addAttribute("secondCategories", categoryService.getAllChildCategories());
        return "members/join";
    }

    @PostMapping("/join")
    public String join(JoinForm joinForm){

        isWrongAuthCode(joinForm.getAuthCode(), authCodeRepo.get(joinForm.getEmail()));
        isPasswordCorrect(joinForm);

        memberService.join(joinForm);

        return "redirect:/login";
    }


    @GetMapping("/auth")
    @ResponseBody
    public String sendAuthEmail(@RequestParam String to) throws Exception {
        String code = emailService.sendSimpleMessage(to);
//        authCodeRepo.put(to, code);
        log.info("code : {}", code);
        return code;
    }

    /***
     * 회원정보 수정 페이지로 이동
     * TODO
     * 현재 임의로 memberId를 지정하여 멤버 상세정보를 조회하지만
     * 세션 적용 후 세션을 통해 memberId나 이메일을 가져와서 상세정보 조회하는걸로 변경 필요
     */
    @GetMapping("/edit")
    public String updateMemberForm(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        // 현재 회원 정보 출력
        Member member = memberService.findMember(1L);

        MemberUpdateForm updateForm = new MemberUpdateForm();
        updateForm.setId(member.getId());
        updateForm.setEmail(member.getEmail());
        updateForm.setNickname(member.getNickname());
        updateForm.setCategory(member.getCategory());

        model.addAttribute("member", loginMember);
        model.addAttribute("updateForm", updateForm);
        return "members/updateMemberForm";
    }

    /***
     * 회원정보 수정
     */
    @PostMapping("/edit")
    public String updateMemberInfo(MemberUpdateForm updateForm, Model model){
        memberService.updateMemberInfo(updateForm);
        return "redirect:/";
    }


    /***
     * 회원정보 조회
     */
    @GetMapping("/mypage")
    public String mypage(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
            Model model)
    {
        MyPageForm memberInfo = memberService.mypage(loginMember.getId());
        model.addAttribute("member", loginMember);
        model.addAttribute("mypage", memberInfo);
        return "members/myPage";
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
    public String deleteMember(
            @PathVariable("memberId") Long memberId,
            HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        if(session != null) session.invalidate();

        postService.deleteByWriter(memberId);
        memberService.deleteMember(memberService.findMember(memberId));

        return "redirect:/";
    }

    private void isPasswordCorrect(JoinForm form) {

        if(!form.getPassword().equals(form.getConfirmPassword())) {
        }
    }

    public void isWrongAuthCode(String formCode, String realCode) {
        if (!formCode.equals(realCode)) {
            throw new IllegalStateException("입력한 인증번호가 다릅니다.");
        }
    }

}
