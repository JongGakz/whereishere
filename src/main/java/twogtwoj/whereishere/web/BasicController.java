package twogtwoj.whereishere.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import twogtwoj.whereishere.domain.Category;
import twogtwoj.whereishere.domain.Company;
import twogtwoj.whereishere.domain.Member;
import twogtwoj.whereishere.service.CompanyService;
import twogtwoj.whereishere.service.MemberService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class BasicController {

    private final MemberService memberService;

    private final CompanyService companyService;


    @PostConstruct
    public void initMemberAndCompany() {
        memberService.save(new Member("Jonggak","123123","임종각",LocalDate.of(1993,6,16)));

        memberService.save(new Member("afternoon","321321","최정오",LocalDate.of(1997,4,15)));

        memberService.save(new Member("Gagyeong","154689","박가경",LocalDate.of(1988,12,7)));

        memberService.save(new Member("Gzero","181818","김지영",LocalDate.of(1996,7,6)));


        companyService.save(new Company("kitri","3213212",1838100871L,
                "키트리주식회사","이미지.jpg","세상에서 제일 맛있는 김치찌개병원", Category.HOSPITAL.getDescription()
        ,"경기도 부천시 소삼로 47"));

        companyService.save(new Company("kisri","1818182",1523849210L,
                "한국시설안전연구원","한국.jpg","둘이먹다 하나죽어도 모르는 아메리카노 카페",Category.CAFFE.getDescription()
        ,"울산광역시 동구 전하로 64"));

        companyService.save(new Company("starbucks","starbucks112",6627019215L,
                "별다방","이미지3.jpg","스타벅스를 혐오하는 자만 먹을 수 있는 김치찌개 까페",Category.CAFFE.getDescription()
        ,"전라남도 무안군 해저면 만송로 480"));

        companyService.save(new Company("JeJu","202012",876451231L,
                "제주공항","이미지4.jpg","백화점이지만 파는건 많지 않아요 하지만 푸드코드 김치찌개가 맛있어요",Category.DEPARTMENT.getDescription()
        ,"제주시 공항로 2"));

        companyService.save(new Company("hyundae","061235",1123212624L,
                "현대백화점","이미지5.jpg","지상 최대 크기 백화점",Category.DEPARTMENT.getDescription()
                ,"서울 구로구 디지털로34길 43"));

        companyService.save(new Company("donsam","4662",1423523121L,
                "돈삼이네","이미지6.jpg","서울 최대 맛집 고기팝니다",Category.RESTAURANT.getDescription()
                ,"서울 구로구 디지털로32나길 51"));

        companyService.save(new Company("venti","95972",6514258852L,
                "더 벤티","이미지7.jpg","독보적 사이즈의 커피 팝니다",Category.CAFFE.getDescription()
                ,"구로구 구로동 1124-42"));
        companyService.save(new Company("appzla","33122a",759846591L,
                "애플스토어","이미지8.jpg","애플 기기 50% 세일",Category.DEPARTMENT.getDescription()
                ,"서울 구로구 디지털로32길 43"));

        companyService.save(new Company("todayzokbal","2818b",238478291L,
                "오늘의 족발","이미지9.jpg","회식자리로 베스트 족발 팝니다",Category.RESTAURANT.getDescription()
                ,"서울 구로구 디지털로 306"));

        companyService.save(new Company("healthcare","3919c",664887237L,
                "헬스케어","이미지10.jpg","헬스기기들 다 최신 꺼에용",Category.HEALTH.getDescription()
                ,"서울 구로구 디지털로 288 대륭포스트타워 1차"));

        companyService.save(new Company("sungmo","eyes1359",1593574589L,
                "울산성모안과의원","이미지11.jpg","라식, 노안 백내장하면 울산성모안과병원.",Category.HOSPITAL.getDescription()
                ,"울산 남구 삼산로 201 성모빌딩 2층"));

        companyService.save(new Company("choryang","milmyeon53",2013654756L,
                "해운대초량밀면","이미지12.jpg","해운대 밀면 맛집입니다. 만두도 맛있어요",Category.RESTAURANT.getDescription()
                ,"부산 해운대구 구남로 20 1층"));

        companyService.save(new Company("assemble","coffe4783",4358792113L,
                "어셈블커피로스터즈","이미지13.jpg","분위기 좋은 로스팅전문 루프탑 카페",Category.CAFFE.getDescription()
                ,"경남 밀양시 삼랑진읍 삼랑진로 32"));

        companyService.save(new Company("muscle","like357",9875613345L,
                "머슬팩토리 율하점","이미지14.jpg","김해 최대규모 실평수 360평. 24시 연중무휴 헬스장. PT 전문 트레이너 10명 근무",Category.HEALTH.getDescription()
                ,"경남 김해시 율하3로 38 경보오션빌딩 3층"));

        companyService.save(new Company("dcube","dc15647",3546987123L,
                "디큐브 거제백화점","이미지15.jpg","고객의 행복과 감동을 최우선으로 하는 실내쇼핑센터 디큐브 거제백화점입니다.",Category.DEPARTMENT.getDescription()
                ,"경남 거제시 장평로 12"));

        companyService.save(new Company("cinema45","yjoo10246",1657895213L,
                "롯데시네마 영주","이미지16.jpg","롯데시네마 영주점은 고객에게 행복한 기억을 선사합니다.",Category.THEATER.getDescription()
                ,"경북 영주시 구성로 417"));

        companyService.save(new Company("jjimdark","ssg5478",8951234567L,
                "안동신세계찜닭","이미지17.jpg","안동찜닭거리의 최고의 맛집. 양도 맛도 최고",Category.RESTAURANT.getDescription()
                ,"경북 안동시 번영길 10"));

        companyService.save(new Company("aden","desertgood",1468795126L,
                "아덴","이미지18.jpg","경주보문단지의 뷰 & 베이커리 & 커피 맛집. 데이트 코스로 제격인 대형 한옥 카페",Category.CAFFE.getDescription()
                ,"경북 경주시 보문로 424-34 "));

        companyService.save(new Company("health26","gym5863",6123495491L,
                "BL짐","이미지19.jpg","가격은 저렴하지만 시설은 외국고급헬스장급 운동맛집",Category.HEALTH.getDescription()
                ,"경북 포항시 남구 대이로 109 4층"));

        companyService.save(new Company("debec","db4350",7561548952L,
                "대구백화점 대백프라자점","이미지20.jpg","꿈과 미래가 있는 대구백화점. 고객과 기업가치의 극대화를 추구합니다.",Category.DEPARTMENT.getDescription()
                ,"대구 중구 명덕로 333"));

        companyService.save(new Company("siott","0507",8457692143L,
                "시옷 남원도통점","이미지5.jpg","안녕하세요 남원 도통점시옷입니다. 야끼니꾸 전문점 이자카야입니다.",Category.RESTAURANT.getDescription()
                ,"전북 남원시 큰들길 36 1층"));

        companyService.save(new Company("ouocham","63589",7456821034L,
                "웅참치","이미지7.jpg","참치 맛집, 웅참치입니다. 카페와 함께합니다.",Category.RESTAURANT.getDescription()
                ,"전북 전주시 완산구 산월2길 30"));

        companyService.save(new Company("GymMuse45","86475",5424785413L,
                "GymMuse","이미지8.jpg","최대 규모 헬스장, 운동 최고",Category.HEALTH.getDescription()
                ,"전북 전주시 덕진구 기린대로 499 2층"));

        companyService.save(new Company("horizon","afpe237",7456812354L,
                "지평선시네마","이미지9.jpg","최고의 영화관, 지평선 시네마 최신 영화 가득 볼 수 있습니다. 환영해요",Category.THEATER.getDescription()
                ,"전북 김제시 도작로 224-32 청소년 수련관"));

        companyService.save(new Company("view78move","frdew47",12354218574L,
                "롯데시네마 군산몰","이미지10.jpg","롯데컬처웍스는 고객에게 특별한 감동을 전해드리기 위해 노력합니다.",Category.THEATER.getDescription()
                ,"전북 군산시 조촌로 130 4층"));

        companyService.save(new Company("ca3fe24","*zkvp32",5489657412L,
                "카페라파르","이미지11.jpg","모든 원두는 스페셜티 원두만 사용합니다. 카페라파르에 놀러오세요",Category.CAFFE.getDescription()
                ,"전북 군산시 옥도면 장자도2길 31"));

        companyService.save(new Company("smellcafe","4885",5468574213L,
                "향이난다","이미지12.jpg","커피에서 향이나는 카페 향이난다로 오세요",Category.CAFFE.getDescription()
                ,"전남 담양군 용면 추령로 25 향이난다"));

        companyService.save(new Company("himgym","456fewr",47854126542L,
                "힘피트니스 본점","이미지13.jpg","헬스장은 힘피트니스 본점입니다. 3대 500 원한다면 여기로",Category.HEALTH.getDescription()
                ,"전남 순천시 이수로 319 6, 7층 힘피트니스 본점"));

        companyService.save(new Company("nccompany","7454dfse!",7899635214L,
                "NC백화점 순천점","이미지14.jpg","순천역에서 59번 탑승 시 금방 옵니다. NC백화점 순천점 백화점 인기 많아요",Category.DEPARTMENT.getDescription()
                ,"전남 순천시 비봉2길 22 NC백화점순천점"));

        companyService.save(new Company("imiko","dkalzh18!",1235421568L,
                "플러스아이미코병원","이미지15.jpg","나믿고 나미코 아미코 플러스 아이미코병원 내과입니다. 내과 전문의3명 전문의 항시대기중",Category.HOSPITAL.getDescription()
                ,"전남 순천시 신월큰길 7 플러스아이미코병윈"));

        companyService.save(new Company("hospital","vlqnrhk123",92037401829L,
                "하얀나라피부과의원","피부과.jpg","당신의 피부를 깔끔하게 만들어 드립니다.",Category.HOSPITAL.getDescription()
                ,"강원 속초시 동해대로 4215"));

        companyService.save(new Company("fitness","harder12",2201293294L,
                "핏앤피트니스","피트니스.jpg","저희 헬스장은 렉이 8개 입니다.",Category.HEALTH.getDescription()
                ,"강원 원주시 능라동길 47"));

        companyService.save(new Company("won","1234",9589039470L,
                "바로그의원","의원.jpg","허리진료를 잘합니다.",Category.HOSPITAL.getDescription()
                ,"강원 강릉시 경강로 2090-1 "));

        companyService.save(new Company("gang","gang123",8493027308L,
                "강냉이소쿠리","강냉이.jpg","저희는 아이스크림이 맛있어요",Category.CAFFE.getDescription()
                ,"강원 강릉시 주문진읍 학교담길 32-8"));

        companyService.save(new Company("mandong","man1234",2382967489L,
                "만동제과","제과.jpg","저희는 빵이 맛있습니다.",Category.CAFFE.getDescription()
                ,"강원 강릉시 금성로 6"));

        companyService.save(new Company("coffee12","co9876",1029384753L,
                "테라로사 커피공장 강릉본점","커피공장.jpg","쉽게 커피 즐기기, 커피상식, 커피문화 공유합니다.",Category.CAFFE.getDescription()
                ,"강원 강릉시 구정면 현천길 25"));

        companyService.save(new Company("car12","coo1248",584930273L,
                "강릉자동차극장","자동차극장.jpg","분위기 좋은 자동차 극장입니다.",Category.THEATER.getDescription()
                ,"강원 강릉시 구정면 칠성로 13-8"));

        companyService.save(new Company("movie21","mo1249",948593827L,
                "롯데시네마 동해","롯데시네마.jpg","롯데컬처웍스는 고객에게 특별한 감동을 전해 드리기 위해 영화관\n" +
                "입지선정부터 다양한 서비스까지 인간사랑, 자연/환경사랑,\n" +
                "영화사랑을 신조로 영화관을 운영하고 있습니다.",Category.THEATER.getDescription()
                ,"강원 동해시 한섬로 111-7"));

        companyService.save(new Company("green12","gr380",3802983740L,
                "그린횟집","횟집.jpg","그린횟집은 횟감으로 쓰이는 모든 어종과 기타 해산물은 전국 최고의 청정해역인 동해안에서 갓잡아 올린 신선한 횟감을 주문진, 남애항과 근처 항구에서 직접 입찰 구매하여 공급하고 있습니다.",Category.RESTAURANT.getDescription()
                ,"강원 강릉시 사천면 진리해변길 123"));

        companyService.save(new Company("koreanfood12","ko19209",1920994838L,
                "강릉한정식 하서주미역","강릉한정식.jpg","기쁜날, 좋은날 함께하고 싶은 사람과 먹기 좋은 음식.\n" +
                "내 가족에게 대접하듯 정성들여 준비하겠습니다.",Category.RESTAURANT.getDescription()
                ,"강원 강릉시 난설헌로 73"));

        companyService.save(new Company("siott","0507",8457692143L,
                "시옷 남원도통점","이미지5.jpg","안녕하세요 남원 도통점시옷입니다. 야끼니꾸 전문점 이자카야입니다.",Category.RESTAURANT.getDescription()
                ,"전북 남원시 큰들길 36 1층"));

        companyService.save(new Company("ouocham","63589",7456821034L,
                "웅참치","이미지7.jpg","참치 맛집, 웅참치입니다. 카페와 함께합니다.",Category.RESTAURANT.getDescription()
                ,"전북 전주시 완산구 산월2길 30"));

        companyService.save(new Company("GymMuse45","86475",5424785413L,
                "GymMuse","이미지8.jpg","최대 규모 헬스장, 운동 최고",Category.HEALTH.getDescription()
                ,"전북 전주시 덕진구 기린대로 499 2층"));

        companyService.save(new Company("horizon","afpe237",7456812354L,
                "지평선시네마","이미지9.jpg","최고의 영화관, 지평선 시네마 최신 영화 가득 볼 수 있습니다. 환영해요",Category.THEATER.getDescription()
                ,"전북 김제시 도작로 224-32 청소년 수련관"));

        companyService.save(new Company("view78move","frdew47",12354218574L,
                "롯데시네마 군산몰","이미지10.jpg","롯데컬처웍스는 고객에게 특별한 감동을 전해드리기 위해 노력합니다.",Category.THEATER.getDescription()
                ,"전북 군산시 조촌로 130 4층"));

        companyService.save(new Company("ca3fe24","*zkvp32",5489657412L,
                "카페라파르","이미지11.jpg","모든 원두는 스페셜티 원두만 사용합니다. 카페라파르에 놀러오세요",Category.CAFFE.getDescription()
                ,"전북 군산시 옥도면 장자도2길 31"));

        companyService.save(new Company("smellcafe","4885",5468574213L,
                "향이난다","이미지12.jpg","커피에서 향이나는 카페 향이난다로 오세요",Category.CAFFE.getDescription()
                ,"전남 담양군 용면 추령로 25 향이난다"));

        companyService.save(new Company("himgym","456fewr",47854126542L,
                "힘피트니스 본점","이미지13.jpg","헬스장은 힘피트니스 본점입니다. 3대 500 원한다면 여기로",Category.HEALTH.getDescription()
                ,"전남 순천시 이수로 319 6, 7층 힘피트니스 본점"));

        companyService.save(new Company("nccompany","7454dfse!",7899635214L,
                "NC백화점 순천점","이미지14.jpg","순천역에서 59번 탑승 시 금방 옵니다. NC백화점 순천점 백화점 인기 많아요",Category.DEPARTMENT.getDescription()
                ,"전남 순천시 비봉2길 22 NC백화점순천점"));

        companyService.save(new Company("imiko","dkalzh18!",1235421568L,
                "플러스아이미코병원","이미지15.jpg","나믿고 나미코 아미코 플러스 아이미코병원 내과입니다. 내과 전문의3명 전문의 항시대기중",Category.HOSPITAL.getDescription()
                ,"전남 순천시 신월큰길 7 플러스아이미코병윈"));

        companyService.save(new Company("sungmo","eyes1359",1593574589L,
                "울산성모안과의원","이미지11.jpg","라식, 노안 백내장하면 울산성모안과병원.",Category.HOSPITAL.getDescription()
                ,"울산 남구 삼산로 201 성모빌딩 2층"));

        companyService.save(new Company("choryang","milmyeon53",2013654756L,
                "해운대초량밀면","이미지12.jpg","해운대 밀면 맛집입니다. 만두도 맛있어요",Category.RESTAURANT.getDescription()
                ,"부산 해운대구 구남로 20 1층"));

        companyService.save(new Company("assemble","coffe4783",4358792113L,
                "어셈블커피로스터즈","이미지13.jpg","분위기 좋은 로스팅전문 루프탑 카페",Category.CAFFE.getDescription()
                ,"경남 밀양시 삼랑진읍 삼랑진로 32"));

        companyService.save(new Company("muscle","like357",9875613345L,
                "머슬팩토리 율하점","이미지14.jpg","김해 최대규모 실평수 360평. 24시 연중무휴 헬스장. PT 전문 트레이너 10명 근무",Category.HEALTH.getDescription()
                ,"경남 김해시 율하3로 38 경보오션빌딩 3층"));

        companyService.save(new Company("dcube","dc15647",3546987123L,
                "디큐브 거제백화점","이미지15.jpg","고객의 행복과 감동을 최우선으로 하는 실내쇼핑센터 디큐브 거제백화점입니다.",Category.DEPARTMENT.getDescription()
                ,"경남 거제시 장평로 12"));

        companyService.save(new Company("cinema45","yjoo10246",1657895213L,
                "롯데시네마 영주","이미지16.jpg","롯데시네마 영주점은 고객에게 행복한 기억을 선사합니다.",Category.THEATER.getDescription()
                ,"경북 영주시 구성로 417"));

        companyService.save(new Company("jjimdark","ssg5478",8951234567L,
                "안동신세계찜닭","이미지17.jpg","안동찜닭거리의 최고의 맛집. 양도 맛도 최고",Category.RESTAURANT.getDescription()
                ,"경북 안동시 번영길 10"));

        companyService.save(new Company("aden","desertgood",1468795126L,
                "아덴","이미지18.jpg","경주보문단지의 뷰 & 베이커리 & 커피 맛집. 데이트 코스로 제격인 대형 한옥 카페",Category.CAFFE.getDescription()
                ,"경북 경주시 보문로 424-34 "));

        companyService.save(new Company("health26","gym5863",6123495491L,
                "BL짐","이미지19.jpg","가격은 저렴하지만 시설은 외국고급헬스장급 운동맛집",Category.HEALTH.getDescription()
                ,"경북 포항시 남구 대이로 109 4층"));

        companyService.save(new Company("debec","db4350",7561548952L,
                "대구백화점 대백프라자점","이미지20.jpg","꿈과 미래가 있는 대구백화점. 고객과 기업가치의 극대화를 추구합니다.",Category.DEPARTMENT.getDescription()
                ,"대구 중구 명덕로 333"));

        companyService.save(new Company("hospital","vlqnrhk123",92037401829L,
                "하얀나라피부과의원","피부과.jpg","당신의 피부를 깔끔하게 만들어 드립니다.",Category.HOSPITAL.getDescription()
                ,"강원 속초시 동해대로 4215"));

        companyService.save(new Company("fitness","harder12",2201293294L,
                "핏앤피트니스","피트니스.jpg","저희 헬스장은 렉이 8개 입니다.",Category.HEALTH.getDescription()
                ,"강원 원주시 능라동길 47"));

        companyService.save(new Company("won","1234",9589039470L,
                "바로그의원","의원.jpg","허리진료를 잘합니다.",Category.HOSPITAL.getDescription()
                ,"강원 강릉시 경강로 2090-1 "));

        companyService.save(new Company("gang","gang123",8493027308L,
                "강냉이소쿠리","강냉이.jpg","저희는 아이스크림이 맛있어요",Category.CAFFE.getDescription()
                ,"강원 강릉시 주문진읍 학교담길 32-8"));

        companyService.save(new Company("mandong","man1234",2382967489L,
                "만동제과","제과.jpg","저희는 빵이 맛있습니다.",Category.CAFFE.getDescription()
                ,"강원 강릉시 금성로 6"));

        companyService.save(new Company("coffee12","co9876",1029384753L,
                "테라로사 커피공장 강릉본점","커피공장.jpg","쉽게 커피 즐기기, 커피상식, 커피문화 공유합니다.",Category.CAFFE.getDescription()
                ,"강원 강릉시 구정면 현천길 25"));

        companyService.save(new Company("car12","coo1248",584930273L,
                "강릉자동차극장","자동차극장.jpg","분위기 좋은 자동차 극장입니다.",Category.THEATER.getDescription()
                ,"강원 강릉시 구정면 칠성로 13-8"));

        companyService.save(new Company("movie21","mo1249",948593827L,
                "롯데시네마 동해","롯데시네마.jpg","롯데컬처웍스는 고객에게 특별한 감동을 전해 드리기 위해 영화관" +
                "입지선정부터 다양한 서비스까지 인간사랑, 자연/환경사랑," +
                "영화사랑을 신조로 영화관을 운영하고 있습니다.",Category.THEATER.getDescription()
                ,"강원 동해시 한섬로 111-7"));

        companyService.save(new Company("green12","gr380",3802983740L,
                "그린횟집","횟집.jpg","그린횟집은 횟감으로 쓰이는 모든 어종과 기타 해산물은 전국 최고의 청정해역인 동해안에서갓잡아 올린 신선한 횟감을 주문진, 남애항과 근처 항구에서 직접 입찰 구매하여 공급하고 있습니다.",Category.RESTAURANT.getDescription()
                ,"강원 강릉시 사천면 진리해변길 123"));

        companyService.save(new Company("koreanfood12","ko19209",1920994838L,
                "강릉한정식 하서주미역","강릉한정식.jpg","기쁜날, 좋은날 함께하고 싶은 사람과 먹기 좋은 음식." +
                "내 가족에게 대접하듯 정성들여 준비하겠습니다.",Category.RESTAURANT.getDescription()
                ,"강원 강릉시 난설헌로 73"));

        companyService.save(new Company("hospital","vlqnrhk123",92037401829L,
                "하얀나라피부과의원","피부과.jpg","당신의 피부를 깔끔하게 만들어 드립니다.",Category.HOSPITAL.getDescription()
                ,"강원 속초시 동해대로 4215"));

        companyService.save(new Company("fitness","harder12",2201293294L,
                "핏앤피트니스","피트니스.jpg","저희 헬스장은 렉이 8개 입니다.",Category.HEALTH.getDescription()
                ,"강원 원주시 능라동길 47"));

        companyService.save(new Company("won","1234",9589039470L,
                "바로그의원","의원.jpg","허리진료를 잘합니다.",Category.HOSPITAL.getDescription()
                ,"강원 강릉시 경강로 2090-1 "));

        companyService.save(new Company("gang","gang123",8493027308L,
                "강냉이소쿠리","강냉이.jpg","저희는 아이스크림이 맛있어요",Category.CAFFE.getDescription()
                ,"강원 강릉시 주문진읍 학교담길 32-8"));

        companyService.save(new Company("mandong","man1234",2382967489L,
                "만동제과","제과.jpg","저희는 빵이 맛있습니다.",Category.CAFFE.getDescription()
                ,"강원 강릉시 금성로 6"));

        companyService.save(new Company("coffee12","co9876",1029384753L,
                "테라로사 커피공장 강릉본점","커피공장.jpg","쉽게 커피 즐기기, 커피상식, 커피문화 공유합니다.",Category.CAFFE.getDescription()
                ,"강원 강릉시 구정면 현천길 25"));

        companyService.save(new Company("car12","coo1248",584930273L,
                "강릉자동차극장","자동차극장.jpg","분위기 좋은 자동차 극장입니다.",Category.THEATER.getDescription()
                ,"강원 강릉시 구정면 칠성로 13-8"));

        companyService.save(new Company("movie21","mo1249",948593827L,
                "롯데시네마 동해","롯데시네마.jpg","롯데컬처웍스는 고객에게 특별한 감동을 전해 드리기 위해 영화관\n" +
                "입지선정부터 다양한 서비스까지 인간사랑, 자연/환경사랑,\n" +
                "영화사랑을 신조로 영화관을 운영하고 있습니다.",Category.THEATER.getDescription()
                ,"강원 동해시 한섬로 111-7"));

        companyService.save(new Company("green12","gr380",3802983740L,
                "그린횟집","횟집.jpg","그린횟집은 횟감으로 쓰이는 모든 어종과 기타 해산물은 전국 최고의 청정해역인 동해안에서 갓잡아 올린 신선한 횟감을 주문진, 남애항과 근처 항구에서 직접 입찰 구매하여 공급하고 있습니다.",Category.RESTAURANT.getDescription()
                ,"강원 강릉시 사천면 진리해변길 123"));

        companyService.save(new Company("koreanfood12","ko19209",1920994838L,
                "강릉한정식 하서주미역","강릉한정식.jpg","기쁜날, 좋은날 함께하고 싶은 사람과 먹기 좋은 음식.\n" +
                "내 가족에게 대접하듯 정성들여 준비하겠습니다.",Category.RESTAURANT.getDescription()
                ,"강원 강릉시 난설헌로 73"));

    }
}
