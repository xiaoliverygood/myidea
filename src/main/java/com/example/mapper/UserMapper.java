package com.example.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("INSERT INTO LinkActirity (id, userEmail, ActivityTerminated, SignInTime, SignOutTime) VALUES (#{id},#{userEmail},#{ActivityTerminated},#{SignInTime},#{SignOutTime})")
   void UserLinkActivity(@Param("id") int id, @Param("userEmail") String userEmail,@Param("ActivityTerminated") String ActivityTerminated,@Param("SignInTime") Date SignInTime, @Param("SignOutTime") Date SignOutTime);

    @Update("UPDATE LinkActirity SET SignInTime = #{SingInTime} WHERE id = #{id} AND userEmail=#{userEmail}")
   void UserSingIn(@Param("SingInTime")Date SingInTime,@Param("id") int id,@Param("userEmail") String userEmail);
    @Update("UPDATE LinkActirity SET SignOutTime = #{SignOutTime} , ActivityTerminated = \"YES\" WHERE id = #{id} AND userEmail = #{userEmail}")
  void UserSingOut(@Param("SignOutTime") Date SignOutTime, @Param("id") int id, @Param("userEmail") String userEmail);

    @Select("select SignInTime from LinkActirity where id=#{id} And userEmail=#{userEmail}")
    Date SignInTimeByUserEmailandId(@Param("id") int id, @Param("userEmail")String userEmail);
}




