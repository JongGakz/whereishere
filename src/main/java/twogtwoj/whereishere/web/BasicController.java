package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import twogtwoj.whereishere.domain.Category;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.service.CompanyService;
import twogtwoj.whereishere.service.MemberService;
import twogtwoj.whereishere.service.ReviewPostService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Controller
@RequestMapping
@RequiredArgsConstructor

public class BasicController {
    private final MemberService memberService;
    private final CompanyService companyService;

    private final ReviewPostService reviewPostService;

    @PostConstruct
    public void init() throws Exception {
        memberService.save(new Member("Jonggak", "123123", "임종각", LocalDate.of(1993, 6, 16)));

        memberService.save(new Member("afternoon", "321321", "최정오", LocalDate.of(1997, 4, 15)));

        memberService.save(new Member("Gagyeong", "154689", "박가경", LocalDate.of(1988, 12, 7)));

        memberService.save(new Member("Gzero", "181818", "김지영", LocalDate.of(1996, 7, 6)));


        companyService.save(new Company("kitri", "3213212", 1838100871L,
                "키트리주식회사", "이미지.jpg", "세상에서 제일 맛있는 김치찌개병원", Category.HOSPITAL.getDescription()
                , "경기도 부천시 소삼로 47"));

        companyService.save(new Company("kisri", "1818182", 1838100871L,
                "한국시설안전연구원", "한국.jpg", "둘이먹다 하나죽어도 모르는 아메리카노 카페", Category.CAFFE.getDescription()
                , "울산광역시 동구 전하로 64"));

        companyService.save(new Company("starbucks", "starbucks112", 1838100871L,
                "별다방", "이미지3.jpg", "스타벅스를 혐오하는 자만 먹을 수 있는 김치찌개 까페", Category.CAFFE.getDescription()
                , "전라남도 무안군 해저면 만송로 480"));

        companyService.save(new Company("JeJu", "202012", 1838100871L,
                "제주공항", "이미지4.jpg", "백화점이지만 파는건 많지 않아요 하지만 푸드코드 김치찌개가 맛있어요", Category.DEPARTMENT.getDescription()
                , "제주시 공항로 2"));

        companyService.save(new Company("sungmo", "eyes1359", 1593574589L,
                "울산성모안과의원", "이미지11.jpg", "라식, 노안 백내장하면 울산성모안과병원.", Category.HOSPITAL.getDescription()
                , "울산 남구 삼산로 201 성모빌딩 2층"));

        companyService.save(new Company("choryang", "milmyeon53", 2013654756L,
                "해운대초량밀면", "이미지12.jpg", "해운대 밀면 맛집입니다. 만두도 맛있어요", Category.RESTAURANT.getDescription()
                , "부산 해운대구 구남로 20 1층"));

        companyService.save(new Company("assemble", "coffe4783", 4358792113L,
                "어셈블커피로스터즈", "이미지13.jpg", "분위기 좋은 로스팅전문 루프탑 카페", Category.CAFFE.getDescription()
                , "경남 밀양시 삼랑진읍 삼랑진로 32"));

        companyService.save(new Company("muscle", "like357", 9875613345L,
                "머슬팩토리 율하점", "이미지14.jpg", "김해 최대규모 실평수 360평. 24시 연중무휴 헬스장. PT 전문 트레이너 10명 근무", Category.HEALTH.getDescription()
                , "경남 김해시 율하3로 38 경보오션빌딩 3층"));

        companyService.save(new Company("dcube", "dc15647", 3546987123L,
                "디큐브 거제백화점", "이미지15.jpg", "고객의 행복과 감동을 최우선으로 하는 실내쇼핑센터 디큐브 거제백화점입니다.", Category.DEPARTMENT.getDescription()
                , "경남 거제시 장평로 12"));

        companyService.save(new Company("cinema45", "yjoo10246", 1657895213L,
                "롯데시네마 영주", "이미지16.jpg", "롯데시네마 영주점은 고객에게 행복한 기억을 선사합니다.", Category.THEATER.getDescription()
                , "경북 영주시 구성로 417"));

        companyService.save(new Company("jjimdark", "ssg5478", 8951234567L,
                "안동신세계찜닭", "이미지17.jpg", "안동찜닭거리의 최고의 맛집. 양도 맛도 최고", Category.RESTAURANT.getDescription()
                , "경북 안동시 번영길 10"));

        companyService.save(new Company("aden", "desertgood", 1468795126L,
                "아덴", "이미지18.jpg", "경주보문단지의 뷰 & 베이커리 & 커피 맛집. 데이트 코스로 제격인 대형 한옥 카페", Category.CAFFE.getDescription()
                , "경북 경주시 보문로 424-34 "));

        companyService.save(new Company("health26", "gym5863", 6123495491L,
                "BL짐", "이미지19.jpg", "가격은 저렴하지만 시설은 외국고급헬스장급 운동맛집", Category.HEALTH.getDescription()
                , "경북 포항시 남구 대이로 109 4층"));

        companyService.save(new Company("debec", "db4350", 7561548952L,
                "대구백화점 대백프라자점", "이미지20.jpg", "꿈과 미래가 있는 대구백화점. 고객과 기업가치의 극대화를 추구합니다.", Category.DEPARTMENT.getDescription()
                , "대구 중구 명덕로 333"));

/*        MultipartFile multipartFile = null;
        reviewPostService.post(new ReviewPost("안동신세계찜닭","박가경", "진짜 맛있어요", "완전 맛있는 안동 찜닭", "이미지1", 0, LocalDate.now()), multipartFile);
        reviewPostService.post(new ReviewPost("키트리주식회사","박가경", "진짜 최고", "이보다 좋은 곳은 없다", "이미지1", 0, LocalDate.now()), );
        reviewPostService.post(new ReviewPost("한국시설안전연구원","박가경", "안전", "안전제일", "이미지1", 0, LocalDate.now()));
        reviewPostService.post(new ReviewPost("별다방","박가경", "커피하면 스타벅스", "슈크림라떼 최고", "이미지1", 0, LocalDate.now()));
        reviewPostService.post(new ReviewPost("제주공항","박가경", "또 가고 싶은 곳", "그리운 그 곳", "이미지1", 0, LocalDate.now()));
        reviewPostService.post(new ReviewPost("울산성모안과의원","박가경", "라식 추천", "추천합니다", "이미지1", 0, LocalDate.now()));
        reviewPostService.post(new ReviewPost("해운대초량밀면","박가경", "밀면 맛집", "만두도 맛있어요", "이미지1", 0, LocalDate.now()));
        reviewPostService.post(new ReviewPost("에셈블커피로스터즈","박가경", "로스팅도 구경가능", "커피는 이곳", "이미지1", 0, LocalDate.now()));
        reviewPostService.post(new ReviewPost("머슬팩토리 율하점","박가경", "이곳에서 운동하면", "운동기구 많아요", "이미지1", 0, LocalDate.now()));
        reviewPostService.post(new ReviewPost("롯데시네마 영주","박가경", "역시 롯데시네마", "", "이미지1", 0, LocalDate.now()));
        reviewPostService.post(new ReviewPost("대구백화점 대백프라자점","박가경", "이보다 좋을 수 없다", "오래오래해주세요", "이미지1", 0, LocalDate.now()));*/
    }
}
