package com.felix.springbootdemo.model;

        import io.swagger.annotations.ApiModel;
        import io.swagger.annotations.ApiModelProperty;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class QuartzEntity implements Serializable {

    private static final long serialVersionUID = -237190490842089873L;
    @ApiModelProperty("任务名称")
    private String jobName;//任务名称
    @ApiModelProperty("任务分组")
    private String jobGroup;//任务分组
    @ApiModelProperty("任务描述")
    private String description;//任务描述
    @ApiModelProperty("/执行类")
    private String jobClassName;//执行类
    @ApiModelProperty("执行时间")
    private String cronExpression;//执行时间
    @ApiModelProperty("任务名称")
    private String triggerName;//任务名称
    @ApiModelProperty("任务状态")
    private String triggerState;//任务状态
    @ApiModelProperty("任务名称 用于修改")
    private String oldJobName;//任务名称 用于修改
    @ApiModelProperty("任务分组 用于修改")
    private String oldJobGroup;//任务分组 用于修改
}
