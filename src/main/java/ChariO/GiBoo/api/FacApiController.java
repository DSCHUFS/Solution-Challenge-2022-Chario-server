package ChariO.GiBoo.api;

import ChariO.GiBoo.domain.Facility;
import ChariO.GiBoo.service.FacService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FacApiController {

    private final FacService facService;

    @Operation(summary = "facility list", description = "기관리스트")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/api/facilities")
    public Result facilities(){
        List<Facility> findFacs = facService.findFacs();
        List<FacDto> collect = findFacs.stream()
                .map(f -> new FacDto(f))
                .collect(Collectors.toList());
        return new Result(collect.size(), collect);
    }
    @Operation(summary = "facility each", description = "기관 상세")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/api/facility/{id}")
    public FacDto findFacility(@PathVariable("id") Long id){
        Facility facility = facService.findOne(id);
        return new FacDto(facility);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private int count;
        private T data;
    }

    @Data
    static class FacDto {
        private String f_name;
        private String f_intro;
        private int f_minimal;
        private String f_home;
        private String f_ars;
        private String f_phone;
        private String f_pay;
        private String f_logo;

        public FacDto(Facility f){
            this.f_name = f.getF_name();
            this.f_intro = f.getF_intro();
            this.f_minimal = f.getF_minimal();
            this.f_home = f.getF_home();
            this.f_ars = f.getF_ars();
            this.f_phone = f.getF_phone();
            this.f_pay = f.getF_pay();
            this.f_logo = f.getF_logo();
        }
    }

    @Data
    @AllArgsConstructor
    static class GetFacResponse {
        private String f_name;
    }
}
