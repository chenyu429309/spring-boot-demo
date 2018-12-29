package com.felix.springbootdemo.model;

        import io.swagger.annotations.ApiModel;
        import io.swagger.annotations.ApiModelProperty;
        import lombok.Data;
        import org.springframework.format.annotation.DateTimeFormat;

        import java.io.Serializable;
        import java.time.LocalDateTime;

/**
 * 作者 : Felix
 * 创建时间 : 2018-09-14 11:26
 */
@Data
@ApiModel
public class PageData implements Serializable {

    private static final long serialVersionUID = 7396428071483400586L;
    @ApiModelProperty("页数")
    private Integer pageSize = 10;
    @ApiModelProperty("每页条数")
    private Integer pageNum = 1;
    @ApiModelProperty("排序方式（多排序使用，隔开，排序字段与排序方式之间使用空格隔开）")
    private String orderBy;
    @ApiModelProperty("查询名称")
    private String searchName;
    @ApiModelProperty("查询字段")
    private String searchValue;
    @ApiModelProperty("起始时间")
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private LocalDateTime startTime;
    @ApiModelProperty("结束时间")
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm:ss")
    private LocalDateTime endTime;

}
