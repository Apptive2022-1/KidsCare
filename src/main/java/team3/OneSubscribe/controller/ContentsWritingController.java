package team3.OneSubscribe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team3.OneSubscribe.DTO.Pagination;
import team3.OneSubscribe.DTO.WritingDTO;
import team3.OneSubscribe.domain.Answer;
import team3.OneSubscribe.domain.DiseaseName;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.domain.Tag;
import team3.OneSubscribe.domain.Writing;
import team3.OneSubscribe.repository.AnswerRepository;
import team3.OneSubscribe.repository.MemberRepository;
import team3.OneSubscribe.repository.TagRepository;
import team3.OneSubscribe.repository.WritingRepository;
import team3.OneSubscribe.service.AnswerService;
import team3.OneSubscribe.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/contents")
public class ContentsWritingController {

    private final WritingRepository writingRepository;
    private final MemberRepository memberRepository;
    private final TagRepository tagRepository;
    private final AnswerService answerService;
    private final AnswerRepository answerRepository;

    @GetMapping("/writing")
    public String writingPage(Model model, HttpServletRequest request) {
        model.addAttribute("form", new WritingDTO());
        HttpSession sess = request.getSession(false);
        if (sess != null) {
            Member m = (Member) sess.getAttribute("member");
            model.addAttribute("name", m.getName());
        }
        return "writing";
    }

    @PostMapping("/writing/new")
    public String contentsFromWriting(WritingDTO form, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member writer = memberRepository.findByLoginId(((Member) session.getAttribute("member")).getLoginId());

        // 1. writing ??????
        Writing writing = new Writing(form);
        writing.setTitle(form.getTitle());
        writing.setContext(form.getContext());
        writing.setMember(writer);
        writing.setCreateDate(LocalDateTime.now());
        writing.setUpdateDate(LocalDateTime.now()); //TODO ????????? ?????? ???????????????????

        writingRepository.save(writing);

        // 2. tag ??????
        if (Objects.equals(request.getParameter("??????"), "1")) {
            Tag tag1 = new Tag();
            tag1.setDiseaseName(DiseaseName.abdominalPain);
            tag1.setWriting(writing);
            tagRepository.save(tag1);
        }
        if (Objects.equals(request.getParameter("??????"), "1")) {
            Tag tag2 = new Tag();
            tag2.setDiseaseName(DiseaseName.diarrhea);
            tag2.setWriting(writing);
            tagRepository.save(tag2);
        }
        if (Objects.equals(request.getParameter("????????????"), "1")) {
            Tag tag3 = new Tag();
            tag3.setDiseaseName(DiseaseName.nutritionalDisease);
            tag3.setWriting(writing);
            tagRepository.save(tag3);
        }
        if (Objects.equals(request.getParameter("??????"), "1")) {
            Tag tag4 = new Tag();
            tag4.setDiseaseName(DiseaseName.rhinitis);
            tag4.setWriting(writing);
            tagRepository.save(tag4);
        }
        if (Objects.equals(request.getParameter("??????"), "1")) {
            Tag tag5 = new Tag();
            tag5.setDiseaseName(DiseaseName.pneumonia);
            tag5.setWriting(writing);
            tagRepository.save(tag5);
        }
        if (Objects.equals(request.getParameter("??????"), "1")) {
            Tag tag6 = new Tag();
            tag6.setDiseaseName(DiseaseName.asthma);
            tag6.setWriting(writing);
            tagRepository.save(tag6);
        }
        if (Objects.equals(request.getParameter("????????????"), "1")) {
            Tag tag7 = new Tag();
            tag7.setDiseaseName(DiseaseName.allergic);
            tag7.setWriting(writing);
            tagRepository.save(tag7);
        }
        if (Objects.equals(request.getParameter("?????????"), "1")) {
            Tag tag8 = new Tag();
            tag8.setDiseaseName(DiseaseName.atopy);
            tag8.setWriting(writing);
            tagRepository.save(tag8);
        }
        if (Objects.equals(request.getParameter("??????"), "1")) {
            Tag tag9 = new Tag();
            tag9.setDiseaseName(DiseaseName.cephalagia);
            tag9.setWriting(writing);
            tagRepository.save(tag9);
        }
        if (Objects.equals(request.getParameter("?????????"), "1")) {
            Tag tag10 = new Tag();
            tag10.setDiseaseName(DiseaseName.otitisMedia);
            tag10.setWriting(writing);
            tagRepository.save(tag10);
        }
        if (Objects.equals(request.getParameter("?????????"), "1")) {
            Tag tag11 = new Tag();
            tag11.setDiseaseName(DiseaseName.empyema);
            tag11.setWriting(writing);
            tagRepository.save(tag11);
        }
        return "redirect:/contents";
    }

    //????????? ??????
    @GetMapping("")
    public String contents(Model model, @RequestParam(defaultValue = "1") int page, HttpServletRequest request) {
        List<Writing> writings = writingRepository.findAll();

        // ??? ????????? ???
        int totalListCnt = writings.size();

        // ???????????????
        Pagination pagination = new Pagination(totalListCnt, page);

        // DB select start index
        int startIndex = pagination.getStartIndex();

        // ????????? ??? ???????????? ???????????? ?????? ??????
        int pageSize = pagination.getPageSize();

        List<Writing> writingList = writingRepository.findListPaging(startIndex, pageSize);
        Collections.reverse(writingList);
        model.addAttribute("writingList", writingList);
        model.addAttribute("pagination", pagination);
        HttpSession session = request.getSession(false);
        //Member m=null;
        if (session != null) {
            if(session.getAttribute("member") != null) {
                Member m = (Member) session.getAttribute("member");
                model.addAttribute("name", m.getName());
            }else{
                Member m = (Member) session.getAttribute("member");
                model.addAttribute("name", "'????????? ??????'");
            }
        }
        return "posts";
    }

    @GetMapping("/own")
    public String myContents(Model model, @RequestParam(defaultValue = "1") int page, HttpServletRequest request){

        HttpSession session = request.getSession();
        // ????????? ??? ?????? ?????????, ????????? ??????????????? ?????? //
        if(session.getAttribute("member") == null){
            return "login";
        }

        // ????????? ??? ??????
        Member writer = memberRepository.findByLoginId(((Member) session.getAttribute("member")).getLoginId());

        List<Writing> writings = writingRepository.findByMember(writer);

        // ??? ????????? ???
        int totalListCnt = writings.size();

        // ???????????????
        Pagination pagination = new Pagination(totalListCnt, page);

        // DB select start index
        int startIndex = pagination.getStartIndex();

        // ????????? ??? ???????????? ???????????? ?????? ??????
        int pageSize = pagination.getPageSize();

        List<Writing> writingList = writingRepository.findListPagingForOwn(writer, startIndex, pageSize);
        Collections.reverse(writingList);
        model.addAttribute("writingList", writingList);
        model.addAttribute("pagination", pagination);

        return "posts";
    }

    @GetMapping("/{writingId}")
    //??? ?????? ????????????
    public String writingContents(@PathVariable("writingId") Long writingId, Model model, HttpServletRequest request) {
        HttpSession sess = request.getSession(false);
        Member m;
        if (sess != null && (m = (Member) sess.getAttribute("member")) != null) {
            model.addAttribute("isLogined", "true");
            model.addAttribute("m", m);
        }
        else{
            m = new Member();
            m.setLoginId("notLogined");
            model.addAttribute("m", m);
        }

        //?????????
        Writing writing = (Writing) writingRepository.findOneById(writingId);
        model.addAttribute("writing", writing); // ?????? ????????? ????????? id ??????
        model.addAttribute("title", writing.getTitle());//writing?????? ??? ????????? ?????? ????????? ????????? ?????????
        model.addAttribute("context", writing.getContext());

        //????????????
        List<Answer> li = answerService.findAllAnswerByWriting(writing);
        model.addAttribute("li", li);

        //????????????(?????????)
        List<Answer> answers = answerRepository.findByWriting(writing);
        model.addAttribute("answers", answers);

        return "showWriting";
    }

    @GetMapping("/{writingId}/answer")
    public String answer(@PathVariable("writingId") Long writingId, Model model) {
        Writing writing = writingRepository.findOneById(writingId);
        model.addAttribute("writing", writing);

        return "answer";
    }

    @Transactional
    @PostMapping("/{writingId}/answer")
    public String answerForWriting(@PathVariable("writingId") Long writingId, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        Writing writing = writingRepository.findOneById(writingId);
        Answer answer = new Answer();
        answer.setWriting(writing);
        answer.setContext(request.getParameter("content"));
        answer.setCreateDate(LocalDateTime.now());

        if(session.getAttribute("member") == null){
            return "login";
        }
        Member writer = memberRepository.findByLoginId(((Member) session.getAttribute("member")).getLoginId());
        answer.setNickName(writer.getNickName());
        answerRepository.save(answer);
        model.addAttribute("writing", writing);
        return "redirect:/contents/{writingId}";
    }

    @GetMapping("/{writingId}/update")
    public String updateWriting(@PathVariable("writingId") Long writingId, HttpServletRequest request, Model model) {
        HttpSession sess = request.getSession(false);
        if (sess != null && sess.getAttribute("member") != null &&((Member) sess.getAttribute("member")).getLoginId() != null) {//????????? ????????? ???
            Writing writing = writingRepository.findOneById(writingId);
            Member m = (Member) sess.getAttribute("member");
            if (writing.getMember().getLoginId().equals(m.getLoginId())) {//???????????? ?????? ???
                model.addAttribute("isWriter", "true");
                model.addAttribute("writing", writing);
                model.addAttribute("isLogined", "true");
                model.addAttribute("m", (Member) sess.getAttribute("member"));
                WritingDTO dto = new WritingDTO();
                dto.setContext(writing.getContext());
                dto.setTitle(writing.getTitle());
                model.addAttribute("form", dto);//updateWriting.html??? ??????. ????????????.
                return "updateWriting";
            }
            return "notYourWriting";

        }
        return "needLogin";
    }
}
