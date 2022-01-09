package com.jock.unmisa.entity.user;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name = "T_USER")
@Data
public class User {
	
	@Id
	@Column(name="USER_ID")
	private String id;
}
