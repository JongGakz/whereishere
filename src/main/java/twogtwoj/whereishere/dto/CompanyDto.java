package twogtwoj.whereishere.dto;

import lombok.Getter;
import lombok.Setter;
import twogtwoj.whereishere.domain.Company;

@Getter
@Setter
public class CompanyDto {
    private Long companyId;
    private String name;

    public CompanyDto(Company data) {
        this.companyId = data.getId();
        this.name = data.getName();
    }
}
