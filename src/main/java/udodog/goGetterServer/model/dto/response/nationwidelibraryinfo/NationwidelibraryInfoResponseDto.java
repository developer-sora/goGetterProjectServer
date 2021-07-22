package udodog.goGetterServer.model.dto.response.nationwidelibraryinfo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NationwidelibraryInfoResponseDto {

    private Long libraryInfoId;                 // 도서관 정보 식별번호

    private String libraryName;                 // 도서관 이름

    private String cityName;                    // 도시명

    private String sigunguName;                 // 시, 군, 구 이름

    private String libraryType;                 // 도서관 유형

    private String redDay;                      // 도서관 휴일

    private String weekdayStartTime;            // 평일 업무 시작 시간

    private String weekdayEndTime;              // 평일 업무 종료 시간

    private String saturdayStartTime;           // 토요일 업무 시작 시간

    private String saturdayEndTime;             // 토요일 업무 종료 시간

    private String publicHolidayStartTime;      // 공휴일 업무 시작 시간

    private String publicHolidayEndTime;        // 공휴일 업무 종료 시간

    private Long numberOfReadingSeats;          // 열람 좌석수

    private Long numberOfBooks;                 // 자료수(도서)

    private Long numberOfPublications;          // 자료수(연속간행물)

    private Long numberOfNotBooks;              // 자료수(비 도서)

    private Integer loanableCount;              // 대출 가능 도서수

    private Integer loanableDayTime;            // 대출 가능 일수

    private String roadAddress;                 // 소재지 도로명 주소

    private String operationInstitution;        // 운영기관

    private String phoneNumber;                 // 도서관 전화번호

    private String website;                     // 도서관 홈페이지 주소

    private String dataInputDay;                // 정보 입력일

    public NationwidelibraryInfoResponseDto(Long libraryInfoId, String libraryName, String cityName, String sigunguName, String libraryType, String redDay, String weekdayStartTime, String weekdayEndTime, String saturdayStartTime, String saturdayEndTime, String publicHolidayStartTime, String publicHolidayEndTime, Long numberOfReadingSeats, Long numberOfBooks, Long numberOfPublications, Long numberOfNotBooks, Integer loanableCount, Integer loanableDayTime, String roadAddress, String operationInstitution, String phoneNumber, String website, String dataInputDay) {
        this.libraryInfoId = libraryInfoId;
        this.libraryName = libraryName;
        this.cityName = cityName;
        this.sigunguName = sigunguName;
        this.libraryType = libraryType;
        this.redDay = redDay;
        this.weekdayStartTime = weekdayStartTime;
        this.weekdayEndTime = weekdayEndTime;
        this.saturdayStartTime = saturdayStartTime;
        this.saturdayEndTime = saturdayEndTime;
        this.publicHolidayStartTime = publicHolidayStartTime;
        this.publicHolidayEndTime = publicHolidayEndTime;
        this.numberOfReadingSeats = numberOfReadingSeats;
        this.numberOfBooks = numberOfBooks;
        this.numberOfPublications = numberOfPublications;
        this.numberOfNotBooks = numberOfNotBooks;
        this.loanableCount = loanableCount;
        this.loanableDayTime = loanableDayTime;
        this.roadAddress = roadAddress;
        this.operationInstitution = operationInstitution;
        this.phoneNumber = phoneNumber;
        this.website = website;
        this.dataInputDay = dataInputDay;
    } // 생성자 끝
} // class 끝
