package com.baobaotao.domain;

import java.util.Date;

import javax.validation.constraints.*;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;


@XStreamAlias("message")
@Data
public class User {
    
	@XStreamAlias("id")
	@XStreamAsAttribute
	private String userId;
	
	@XStreamAsAttribute
	@Pattern(regexp="w{4,30}")
	private String userName;
	
	@XStreamAsAttribute
	@Pattern(regexp="S{6,30}")
	private String password;
	
	@XStreamAsAttribute
	@Length(min=2,max=100)
	private String realName;
	
	@XStreamAsAttribute
	@Past
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@NotNull
	private Date birthday;
	
	@XStreamAsAttribute
    @DecimalMin(value="1000.00")
    @DecimalMax(value="100000.00")
	@NumberFormat(pattern="#,###.##")
	private long salary;
	
	@XStreamOmitField
	private Dept dept;

}
