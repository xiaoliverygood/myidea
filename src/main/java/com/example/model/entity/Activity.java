package com.example.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@TableName(value ="activity")
@Data
public class Activity implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;

    private Integer time;
    private Date beginTime;
    private Date lateTime;
    private String location;
    private Integer maxpeople;
    private String belongingAdimit;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Activity other = (Activity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getBeginTime() == null ? other.getBeginTime() == null : this.getBeginTime().equals(other.getBeginTime()))
            && (this.getLateTime() == null ? other.getLateTime() == null : this.getLateTime().equals(other.getLateTime()))
            && (this.getLocation() == null ? other.getLocation() == null : this.getLocation().equals(other.getLocation()))
            && (this.getMaxpeople() == null ? other.getMaxpeople() == null : this.getMaxpeople().equals(other.getMaxpeople()))
            && (this.getBelongingAdimit() == null ? other.getBelongingAdimit() == null : this.getBelongingAdimit().equals(other.getBelongingAdimit()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getBeginTime() == null) ? 0 : getBeginTime().hashCode());
        result = prime * result + ((getLateTime() == null) ? 0 : getLateTime().hashCode());
        result = prime * result + ((getLocation() == null) ? 0 : getLocation().hashCode());
        result = prime * result + ((getMaxpeople() == null) ? 0 : getMaxpeople().hashCode());
        result = prime * result + ((getBelongingAdimit() == null) ? 0 : getBelongingAdimit().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", time=").append(time);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", lateTime=").append(lateTime);
        sb.append(", location=").append(location);
        sb.append(", maxpeople=").append(maxpeople);
        sb.append(", belongingAdimit=").append(belongingAdimit);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}