package com.wittymonkey.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
/**
 * 通知，类似于站内信和邮件功能
 * @author neilw
 *
 */
@Entity
@Table(name = "notify")
public class Notify implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="from_user_id")
	private User fromUser;
	
	@OneToMany(targetEntity=NotifyReceiver.class, mappedBy="notify")
	private List<NotifyReceiver> receivers = new ArrayList<NotifyReceiver>();
	
	@Column(name="send_date")
	private Date sendDate;
	
	@OneToMany(targetEntity=NotifyFile.class, mappedBy="notify")
	private List<NotifyFile> files = new ArrayList<NotifyFile>();
	
	// 紧急级别
	@Column
	private Integer level;
	
	// 主题
	@Column
	private String subject;
	
	@Column(columnDefinition="TEXT")
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getFromUser() {
		return fromUser;
	}

	public void setFromUser(User fromUser) {
		this.fromUser = fromUser;
	}

	public List<NotifyReceiver> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<NotifyReceiver> receivers) {
		this.receivers = receivers;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public List<NotifyFile> getFiles() {
		return files;
	}

	public void setFiles(List<NotifyFile> files) {
		this.files = files;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
