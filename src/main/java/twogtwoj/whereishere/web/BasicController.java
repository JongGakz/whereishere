package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twogtwoj.whereishere.domain.*;
import twogtwoj.whereishere.dto.ReviewPostDto;
import twogtwoj.whereishere.file.FileStore;
import twogtwoj.whereishere.repository.ReviewPostRepository;
import twogtwoj.whereishere.service.*;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BasicController {

    private final MemberService memberService;

    private final CompanyServiceImpl companyServiceImpl;

    private final ReviewPostService reviewPostService;

    private final StarService starservice;

    private final CommentService commentService;

    private final FileStore fileStore;

    private final EventPostService eventPostService;

    private final ReviewPostRepository reviewPostRepository;


    // 게시글 보기에서 게시글에 저장된 이미지 가져오는 메서드
    @ResponseBody
    @GetMapping("/image/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(filename));
        return urlResource;
    }

    @PostConstruct
    public void initMemberAndCompany() {

        memberService.save(new Member("anony", "123123", "익명사용자", LocalDate.of(1990, 6, 16)));

        Member member = memberService.save(new Member("Jonggak", "123123", "임종각", LocalDate.of(1993, 6, 16)));

        Member member2 = memberService.save(new Member("Afternoon", "123123", "최정오", LocalDate.of(1997, 4, 15)));

        Member member3 = memberService.save(new Member("Gagyeong", "123123", "박가경", LocalDate.of(1988, 12, 7)));

        Member member4 = memberService.save(new Member("Gzero", "123123", "김지영", LocalDate.of(1996, 7, 6)));

        Member member5 = memberService.save(new Member("Jonghwa", "123123", "임종화", LocalDate.of(1998, 10, 22)));


        companyServiceImpl.save(new Company("kitri", "123123", 1838100871L,
                "키트리병원", "이미지1.png", "시설 최대 병원", Category.HOSPITAL.getDescription()
                , "경기도 부천시 소삼로 47"));

        companyServiceImpl.save(new Company("kisri", "1818182", 1523849210L,
                "스타벅스 소사점", "이미지2.png", "둘이먹다 하나죽어도 모르는 아메리카노 카페", Category.CAFFE.getDescription()
                , "울산광역시 동구 전하로 64"));

        companyServiceImpl.save(new Company("starbucks", "starbucks112", 6627019215L,
                "별다방", "이미지3.png", "스타벅스를 혐오하는 자만 먹을 수 있는 까페", Category.CAFFE.getDescription()
                , "전라남도 무안군 해제면 만송로 480"));

        companyServiceImpl.save(new Company("JeJu", "202012", 876451231L,
                "제주공항", "이미지4.png", "백화점이지만 파는건 많지 않아요 하지만 푸드코드 김치찌개가 맛있어요", Category.DEPARTMENT.getDescription()
                , "제주시 공항로 2"));

        companyServiceImpl.save(new Company("hyundae", "061235", 1123212624L,
                "현대백화점", "이미지5.png", "지상 최대 크기 백화점", Category.DEPARTMENT.getDescription()
                , "서울 구로구 디지털로34길 43"));

        companyServiceImpl.save(new Company("donsam", "4662", 1423523121L,
                "돈삼이네", "이미지6.png", "서울 최대 맛집 고기팝니다", Category.RESTAURANT.getDescription()
                , "서울 구로구 디지털로32나길 51"));

        companyServiceImpl.save(new Company("venti", "95972", 6514258852L,
                "더 벤티", "이미지7.png", "독보적 사이즈의 커피 팝니다", Category.CAFFE.getDescription()
                , "구로구 구로동 1124-42"));
        companyServiceImpl.save(new Company("appzla", "33122a", 759846591L,
                "애플스토어", "이미지8.png", "애플 기기 50% 세일", Category.DEPARTMENT.getDescription()
                , "서울 구로구 디지털로32길 43"));

        companyServiceImpl.save(new Company("todayzokbal", "2818b", 238478291L,
                "오늘의 족발", "이미지9.png", "회식자리로 베스트 족발 팝니다", Category.RESTAURANT.getDescription()
                , "서울 구로구 디지털로 306"));

        companyServiceImpl.save(new Company("healthcare", "3919c", 664887237L,
                "헬스케어", "이미지10.png", "헬스기기들 다 최신 꺼에용", Category.HEALTH.getDescription()
                , "서울 구로구 디지털로 288 대륭포스트타워 1차"));

        companyServiceImpl.save(new Company("sungmo", "eyes1359", 1593574589L,
                "울산성모안과의원", "이미지11.png", "라식, 노안 백내장하면 울산성모안과병원.", Category.HOSPITAL.getDescription()
                , "울산 남구 삼산로 201 성모빌딩 2층"));

        companyServiceImpl.save(new Company("choryang", "milmyeon53", 2013654756L,
                "해운대초량밀면", "이미지12.png", "해운대 밀면 맛집입니다. 만두도 맛있어요", Category.RESTAURANT.getDescription()
                , "부산 해운대구 구남로 20 1층"));

        companyServiceImpl.save(new Company("assemble", "coffe4783", 4358792113L,
                "어셈블커피로스터즈", "이미지13.png", "분위기 좋은 로스팅전문 루프탑 카페", Category.CAFFE.getDescription()
                , "경남 밀양시 삼랑진읍 삼랑진로 32"));

        companyServiceImpl.save(new Company("muscle", "like357", 9875613345L,
                "머슬팩토리 율하점", "이미지14.png", "김해 최대규모 실평수 360평. 24시 연중무휴 헬스장. PT 전문 트레이너 10명 근무", Category.HEALTH.getDescription()
                , "경남 김해시 율하3로 38 경보오션빌딩 3층"));

        companyServiceImpl.save(new Company("dcube", "dc15647", 3546987123L,
                "디큐브 거제백화점", "이미지15.png", "고객의 행복과 감동을 최우선으로 하는 실내쇼핑센터 디큐브 거제백화점입니다.", Category.DEPARTMENT.getDescription()
                , "경남 거제시 장평로 12"));

        companyServiceImpl.save(new Company("cinema45", "yjoo10246", 1657895213L,
                "롯데시네마 영주", "이미지16.png", "롯데시네마 영주점은 고객에게 행복한 기억을 선사합니다.", Category.THEATER.getDescription()
                , "경북 영주시 구성로 417"));

        Company company = companyServiceImpl.save(new Company("jjimdark", "ssg5478", 8951234567L,
                "안동신세계찜닭", "이미지17.png", "안동찜닭거리의 최고의 맛집. 양도 맛도 최고", Category.RESTAURANT.getDescription()
                , "경북 안동시 번영길 10"));

        companyServiceImpl.save(new Company("aden", "desertgood", 1468795126L,
                "아덴", "이미지18.png", "경주보문단지의 뷰 & 베이커리 & 커피 맛집. 데이트 코스로 제격인 대형 한옥 카페", Category.CAFFE.getDescription()
                , "경북 경주시 보문로 424-34 "));

        companyServiceImpl.save(new Company("health26", "gym5863", 6123495491L,
                "BL짐", "이미지19.png", "가격은 저렴하지만 시설은 외국고급헬스장급 운동맛집", Category.HEALTH.getDescription()
                , "경북 포항시 남구 대이로 109 4층"));

        companyServiceImpl.save(new Company("Gzero", "db4350", 7561548952L,
                "대구백화점 대백프라자점", "이미지20.png", "꿈과 미래가 있는 대구백화점. 고객과 기업가치의 극대화를 추구합니다.", Category.DEPARTMENT.getDescription()
                , "대구 중구 명덕로 333"));

        companyServiceImpl.save(new Company("gggzaqs", "0507", 8457692143L,
                "시옷 남원도통점", "이미지21.png", "안녕하세요 남원 도통점시옷입니다. 야끼니꾸 전문점 이자카야입니다.", Category.RESTAURANT.getDescription()
                , "전북 남원시 큰들길 36 1층"));

        companyServiceImpl.save(new Company("ouocham", "63589", 7456821034L,
                "웅참치", "이미지22.png", "참치 맛집, 웅참치입니다. 카페와 함께합니다.", Category.RESTAURANT.getDescription()
                , "전북 전주시 완산구 산월2길 30"));

        companyServiceImpl.save(new Company("horizon", "afpe237", 7456812354L,
                "지평선시네마", "이미지23.png", "최고의 영화관, 지평선 시네마 최신 영화 가득 볼 수 있습니다. 환영해요", Category.THEATER.getDescription()
                , "전북 김제시 도작로 224-32 청소년 수련관"));

        companyServiceImpl.save(new Company("view78move", "frdew47", 12354218574L,
                "롯데시네마 군산몰", "이미지24.png", "롯데컬처웍스는 고객에게 특별한 감동을 전해드리기 위해 노력합니다.", Category.THEATER.getDescription()
                , "전북 군산시 조촌로 130 4층"));

        companyServiceImpl.save(new Company("ca3fe24", "*zkvp32", 5489657412L,
                "카페라파르", "이미지25.png", "모든 원두는 스페셜티 원두만 사용합니다. 카페라파르에 놀러오세요", Category.CAFFE.getDescription()
                , "전북 군산시 옥도면 장자도2길 31"));

        companyServiceImpl.save(new Company("smellcafe", "4885", 5468574213L,
                "향이난다", "이미지26.png", "커피에서 향이나는 카페 향이난다로 오세요", Category.CAFFE.getDescription()
                , "전남 담양군 용면 추령로 25 향이난다"));

        companyServiceImpl.save(new Company("himgym", "456fewr", 47854126542L,
                "힘피트니스 본점", "이미지27.png", "헬스장은 힘피트니스 본점입니다. 3대 500 원한다면 여기로", Category.HEALTH.getDescription()
                , "전남 순천시 이수로 319 6, 7층 힘피트니스 본점"));

        companyServiceImpl.save(new Company("nccompany", "7454dfse!", 7899635214L,
                "NC백화점 순천점", "이미지28.png", "순천역에서 59번 탑승 시 금방 옵니다. NC백화점 순천점 백화점 인기 많아요", Category.DEPARTMENT.getDescription()
                , "전남 순천시 비봉2길 22 NC백화점순천점"));

        companyServiceImpl.save(new Company("imiko", "dkalzh18!", 1235421568L,
                "플러스아이미코병원", "이미지29.png", "나믿고 나미코 아미코 플러스 아이미코병원 내과입니다. 내과 전문의3명 전문의 항시대기중", Category.HOSPITAL.getDescription()
                , "전남 순천시 신월큰길 7 플러스아이미코병윈"));

        companyServiceImpl.save(new Company("hospital", "vlqnrhk123", 92037401829L,
                "하얀나라피부과의원", "이미지30.png", "당신의 피부를 깔끔하게 만들어 드립니다.", Category.HOSPITAL.getDescription()
                , "강원 속초시 동해대로 4215"));

        companyServiceImpl.save(new Company("fitness", "harder12", 2201293294L,
                "핏앤피트니스", "이미지31.png", "저희 헬스장은 렉이 8개 입니다.", Category.HEALTH.getDescription()
                , "강원 원주시 능라동길 47"));

        companyServiceImpl.save(new Company("won", "1234", 9589039470L,
                "바로그의원", "이미지32.png", "허리진료를 잘합니다.", Category.HOSPITAL.getDescription()
                , "강원 강릉시 경강로 2090-1 "));

        companyServiceImpl.save(new Company("gang", "gang123", 8493027308L,
                "강냉이소쿠리", "이미지33.png", "저희는 아이스크림이 맛있어요", Category.CAFFE.getDescription()
                , "강원 강릉시 주문진읍 학교담길 32-8"));

        companyServiceImpl.save(new Company("mandong", "man1234", 2382967489L,
                "만동제과", "이미지34.png", "저희는 빵이 맛있습니다.", Category.CAFFE.getDescription()
                , "강원 강릉시 금성로 6"));

        companyServiceImpl.save(new Company("coffee12", "co9876", 1029384753L,
                "테라로사 커피공장 강릉본점", "이미지35.png", "쉽게 커피 즐기기, 커피상식, 커피문화 공유합니다.", Category.CAFFE.getDescription()
                , "강원 강릉시 구정면 현천길 25"));

        companyServiceImpl.save(new Company("car12", "coo1248", 584930273L,
                "강릉자동차극장", "이미지36.png", "분위기 좋은 자동차 극장입니다.", Category.THEATER.getDescription()
                , "강원 강릉시 구정면 칠성로 13-8"));

        companyServiceImpl.save(new Company("movie21", "mo1249", 948593827L,
                "롯데시네마 동해", "이미지37.png", "롯데컬처웍스는 고객에게 특별한 감동을 전해 드리기 위해 영화관\n" +
                "입지선정부터 다양한 서비스까지 인간사랑, 자연/환경사랑,\n" +
                "영화사랑을 신조로 영화관을 운영하고 있습니다.", Category.THEATER.getDescription()
                , "강원 동해시 한섬로 111-7"));

        companyServiceImpl.save(new Company("green12", "gr380", 3802983740L,
                "그린횟집", "이미지38.png", "그린횟집은 횟감으로 쓰이는 모든 어종과 기타 해산물은 전국 최고의 청정해역인 동해안에서 갓잡아 올린 신선한 횟감을 주문진, 남애항과 근처 항구에서 직접 입찰 구매하여 공급하고 있습니다.", Category.RESTAURANT.getDescription()
                , "강원 강릉시 사천면 진리해변길 123"));

        companyServiceImpl.save(new Company("koreanfood12", "ko19209", 1920994838L,
                "강릉한정식 하서주미역", "이미지39.png", "기쁜날, 좋은날 함께하고 싶은 사람과 먹기 좋은 음식.\n" +
                "내 가족에게 대접하듯 정성들여 준비하겠습니다.", Category.RESTAURANT.getDescription()
                , "강원 강릉시 난설헌로 73"));


        starservice.save(new Star(member3, company, 4.0));
        starservice.save(new Star(member2, company, 3.0));

        commentService.save(new Comment(member2, company, "와 존맛", LocalDateTime.of(2023,5,1,12,30,52)));
        commentService.save(new Comment(member2, company, "정말 친절하세요", LocalDateTime.of(2023,6,2,7,40,52)));
        commentService.save(new Comment(member3, company, "접근성이 떨어져요", LocalDateTime.of(2023,7,3,6,50,52)));
        commentService.save(new Comment(member4, company, "가성비가 좋아요", LocalDateTime.of(2023,6,1,5,20,52)));

        commentService.save(new Comment(member2, company, "사장님이 재밌어요", LocalDateTime.of(2023,8,16,5,10,52)));
        commentService.save(new Comment(member2, company, "가본적은 없지만 가보고 싶어요", LocalDateTime.of(2023,9,16,1,20,52)));
        commentService.save(new Comment(member3, company, "카페라뗴가 맛있어요", LocalDateTime.of(2023,10,1,12,5,52)));
        commentService.save(new Comment(member4, company, "너무 좋아요", LocalDateTime.of(2023,11,15,3,5,52)));


        eventPostService.save(new EventPost(companyServiceImpl.findCompanyByCompanyName("핏앤피트니스"), "새해 이벤트", "1월 중에 등록하신분들은 회원권 40%할인 해드립니다.", "이미지2.png", LocalDate.of(2022, 01, 10)));
        eventPostService.save(new EventPost(companyServiceImpl.findCompanyByCompanyName("강냉이소쿠리"), "아메리카노 반값 이벤트", "한달 동안 아메리카노에 한해서 반값 이벤트 합니다.", "이미지2.png", LocalDate.of(2022, 02, 13)));
        eventPostService.save(new EventPost(companyServiceImpl.findCompanyByCompanyName("만동제과"), "빵 6개 이벤트", "빵 6개를 한번에 사시면 서비스로 2개를 드려요", "이미지2.png", LocalDate.of(2021, 05, 13)));
        eventPostService.save(new EventPost(companyServiceImpl.findCompanyByCompanyName("테라로사 커피공장 강릉본점"), "아침이벤트", "아침에는 아메리카노가 이천원 입니다.", "이미지2.png", LocalDate.of(2021, 12, 27)));
        eventPostService.save(new EventPost(companyServiceImpl.findCompanyByCompanyName("강릉자동차극장"), "팝콘 세트 이벤트", "팝콘 세트 20%할인합니다.", "이미지2.png", LocalDate.of(2020, 07, 24)));
        eventPostService.save(new EventPost(companyServiceImpl.findCompanyByCompanyName("롯데시네마 동해"), "조조영화 특별 할인", "조조영화는 10%할인 합니다.", "이미지2.png", LocalDate.of(2022, 01, 05)));
        eventPostService.save(new EventPost(companyServiceImpl.findCompanyByCompanyName("그린횟집"), "제철생선 주문시 추가이벤트", "제철생선을 시킬시 광어와 우럭회를 추가로 드려요", "이미지2.png", LocalDate.of(2022, 04, 15)));
        eventPostService.save(new EventPost(companyServiceImpl.findCompanyByCompanyName("강릉한정식 하서주미역"), "10인이상 단체손님은 음료수 무료", "10인 이상 단체손님 방문시 음료수를 무료로 제공해 드립니다.", "이미지2.png", LocalDate.of(2019, 11, 11)));

        eventPostService.save(new EventPost(companyServiceImpl.findCompanyByCompanyName("별다방"), "아침이벤트", "아침에는 아메리카노가 이천원 입니다.", "이미지3.png", LocalDate.of(2023, 5, 1)));

        reviewPostRepository.save(new ReviewPost(member2, company, "돈삼이네", member2.getName(), "암사동 삼겹살 생고기 맛집 돈삼이네 암사역과 굽은다리역 중간쯤에 위치한 돈삼이네.", "돌판에 고기, 김치 부추를 깔아 줘서 같이 구워먹으면 진짜 꿀맛! 기본반찬도 맛있고 가격도 착해서 맛있어요 마지막은 볶음밥 국룰이죠! 근데 여기에 치즈 볶음밥까지 먹는다면? 더이상 말이 필요없죠!! 위에 김가루까지 덮어준다? 말해뭐해... 암사동에서 생고기 삼겹살을 먹고 싶다면 돈삼이네로!", "이미지3.png", "이미지2.png", 3, LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member, company, "별다방", member.getName(), "둘이먹다 하나죽어도 모르는 별다방 까페", "사장님이 친절해요", "이미지3.png", "이미지2.png", 3, LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member3, company, "별다방", member3.getName(), "여기는 좀 별로더라고요", "사장님이 불친절해요", "이미지3.png", "이미지2.png", 3, LocalDate.now().minusWeeks(1)));
        reviewPostRepository.save(new ReviewPost(member4, company, "별다방", member4.getName(), "기가막히고 코가막히게 맛있는 곳", "아메리카노를 공짜로 주더라고요", "이미지3.png", "이미지2.png", 3, LocalDate.now().minusWeeks(4)));
        reviewPostRepository.save(new ReviewPost(member5, company, "별다방", member5.getName(), "대박사건", "Wow. it is only one korean coffee , and so kind master", "이미지3.png", "이미지2.png", 3, LocalDate.now().minusWeeks(6)));


        reviewPostRepository.save(new ReviewPost(member, company, "지평선시네마", member.getName(), "작은 영화관 대한민국 1호점", " 좌석은 65석, 34석 뿐이지만 집중도 잘되고 조명, 사운드, 스피커 등 시스템도 뒤떨어지지 않아요. 대형 영화관에 작은 상영관보다 이곳이 훨씬 좋아요. 내부는 예전에 가본 서울극장과 비슷한 느낌이에요. 그리고 먹거리도 저렴한데 맛도 있어요. 올망졸망 작은 공간이지만 알차게 되어있어요. 그리고 큰 상영관과 동시상영할 정도로 개봉속도로 빨라요.", "지평선시네마.jpg", "지평선시네마2.jpg",6,LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member3, company, "울산성모안과의원", member3.getName(), "알레르기로 병원다녀왔어요ㅠㅠ", "예전에 리식 수술하러 간 적 있었는데 이번에는 안구건조증이랑 알러지 치료하러 다녀왔어요! 선생님이 친절하고 꼼꼼하게 진료를 잘해주셔서 이제 다른 곳에 못가겠어요. 근데 갈 때마다 대기시간이 길어서 넉넉히 시간 잡고 가야됩니다.... 그래도 진료를 잘해주시니 그 정도는 기다릴 수 있어요! ", "울산성모안과1.jpg", "울산성모안과2.jpg",3,LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member2, company, "돈삼이네",member2.getName(),"암사동 삼겹살 생고기 맛집 돈삼이네 암사역과 굽은다리역 중간쯤에 위치한 돈삼이네.", "돌판에 고기, 김치 부추를 깔아 줘서 같이 구워먹으면 진짜 꿀맛! 기본반찬도 맛있고 가격도 착해서 맛있어요 마지막은 볶음밥 국룰이죠! 근데 여기에 치즈 볶음밥까지 먹는다면? 더이상 말이 필요없죠!! 위에 김가루까지 덮어준다? 말해뭐해... 암사동에서 생고기 삼겹살을 먹고 싶다면 돈삼이네로!", "돈삼이네1.jpg", "돈삼이네2.jpg" ,3,LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member, company, "어셈블커피로스터즈", member.getName(), "뷰 좋은 카페 어셈블 커피 로스터즈", "삼랑진 근처 갔다가 추천 받고 간 곳. 커피도 맛있고 디저트도 맛있어요. 특히 뷰가 너무 예쁜데 넉동강 철교가 한 눈에 보여서 더 좋았어요~!^^ 딸기 계절이라 기대하고 딸기 제품을 시켰는데 냉동딸기 아닌 생딸기여서 식감도 당도도 너무 완벽했어요. 빵도 부드럽고 크림도 느끼하지 않고 너무 맛있는데 커피도 고소하니 너무 좋아요", "어셈블커피로스터즈.jpg", "어셈블커피로스터즈2.jpg",3,LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member3, company, "안동신세계찜닭", member3.getName(), "여러 명이서 갔는데 다들 너무 좋아했어요", "나름 7명이어서 예약하고 사장님께 전화드리고 갔는데 친구들이 늦게 와서 기다리고 있는데 바로 자리 만들어주시고 사람이 많았는데도 계속 여쭤봐도 친절히 대답해 주셨어요. 그리고 7명이서 대자로 안 맵게 부탁드린다고(매운 걸 못 먹어서...) 했는데 아이도 맛있게 잘 먹었어요! 그리고 저희 제일 큰 걸로 하나만 시켰는데 두 개로 주시길래 저희 큰 걸로 하나 시켰다고 하니까 사장님이 테이블이 2개라서 나눠서 주셨어요. 배려심에 너무 감동.. 양도 진짜많고 맛도 있어요. 또 방문할 예정이에요!", "안동신세계찜닭1.jpg", "안동신세계찜닭2.jpg",5,LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member, company, "카페라파르",member2.getName(),"장자도를 간다면 꼭 한 번 들러보세요", "섬 안에 있는 카페 중에 약간 인스타 갬성 카페에요. 어디론가 떠나고 싶을 때 바람쐬기 좋은 곳이에요. 규모가 작아서 옹기종기 모여있는 느낌이지만 오선뷰라서 물멍을 할 수도 있고 그 덕에 힐링하기 좋은 느낌이에요. 한라봉 음료가 맛있어서 주문했는데 괜찮았고 디저트도 맛있어요. 장자도 가신다면 추천드려요.", "카페라파르.jpg", "카페라파르2.jpg",8,LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member2, company, "플러스아이미코병원",member2.getName(),"너무 친절하셔서 좋아요", "내과 진료 보러 갔는데 직원들도 친절하고 원장님도 자세한 설명에 이해가 잘 되었어요. 전체적인 분위기가 화기애애해서 더 좋았어요. 한동안 낫질 않아서 불편했는데 병원 바꾸고 여기서 처방받은 약을 먹으니 금방 좋아졌어요. 이전에 아이가 아파서 내원해서 진료 받을 때도 꼼곰하게 봐주시고 수액을 놓아주셔서 빠르게 호전되었었는데 앞으로 이 병원만 다녀야겠어요. ", "플러스아이미코병원2.jpg", "플러스아이미코병원.jpg",2,LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member, company, "NC 백화점 순천점", member.getName(), "쇼핑하기 좋아요", "가성비 좋은 예쁜 옷 구경하기도 좋고 주차도 편하고 브랜드도 다양해서 쇼핑하기 너무좋아요. 이번에는 얼마 전에 오픈만 젝시믹스를 다녀왔어요. 새로 오픈해서 오픈행사를 하고 있더라고요. 덕분에 저렴하게 득템할 수 있었어요. nc 순천점은 안에 밥집도 다양해서 쇼핑 후 허기지면 바로 밥을 먹으러 갈 수 있다는 것도 장점 아닌 장점이에요. 유아휴게실도 있어서 아이들 기저귀 갈기도 편하고 전자레인지도 있어서 아이들 밥 먹이기도 좋아요.", "NC순천점.jpg", "NC순천점2.jpg",0,LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member3, company, "핏앤피트니스", member3.getName(), "트레이너 분들이 너무 친절해요", "어깨가 많이 굽어있어서 운동하기 시작했는데 운동하면서 펴지는게 느껴지는게 너무 신기해요. 운동 시작할 때 헬스는 너무 지루하다는 생각이 있어서 엄청 고민했는데 고민을 왜 했나 싶을 정도로 꼼꼼하게 잘 봐주시고 친절하게 설명해주셔서 혼자 운동할 때도 잘할 수 있을 것 같아서 너무좋아요. 식단도 잘 짜주셔서 식단 지키는 것에 어려움도 없어요.", "핏앤피트니스.jpg", "핏앤피트니스2.jpg",2,LocalDate.now().minusWeeks(2)));
        reviewPostRepository.save(new ReviewPost(member2, company, "테라로사 커피공장 강릉본점",member2.getName(),"커피를 좋아한다면 한 번쯤 가기 좋은 곳", "늘 교통편이 불편해서 가기 힘든 곳이어서 뚜벅이로 가려니 버스시간을 맞추긴 너무 힘들고 차를 가져가자니 장거리 운전이 부담되어서 몇 년간 언젠가는 가야지 마음만 먹고 있었는데 이번에 친구랑 강릉에 가면서 이번에는 버스를 타고 가지 말고 차를 끌고 가서 뚜벅이로 가기 힘든 곳을 가보자라고 마음 먹고 갔는데 고민을 많이하고 망설인게 무색할 만큼 카페는 너무 예뻤고 날도 너무 좋았어요. 그리고 옆에 레스토랑도 있는데 생각보다 너무 맛있었고 후식으로 커피를 마셨는데 커피 향도 너무 좋고 잘 내려주셔서 한 잔만 마시는게 아쉬울 정도였어서 다음에도 또 오고 싶다는 생각이 들더라고요. 그래서 조만간 또 갈 생각 입니다.", "테라로사.jpg", "테라로사2.jpg",10,LocalDate.now().minusWeeks(2)));
    }
}
