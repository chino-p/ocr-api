package com.trilight.ocr.model.pojo.purchase;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 送货单确认文件
 */
@TableName("delivery_confirm_file")
@Data
public class DeliveryConfirmFileDO {

    @TableId(type= IdType.AUTO)
    private Long id;

    private Long deliveryId;

    private String fileName;

    private String ossFileName;

    private LocalDateTime createTime;
}
