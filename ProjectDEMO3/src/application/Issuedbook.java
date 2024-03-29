package application;

import java.time.LocalDate;

public class Issuedbook {
	private String bookName = null;
	private LocalDate issueDate = null;
    private int userId = 0;
    private int bookId = 0;
    
    public Issuedbook(String bookName, LocalDate issueDate, int userId, int bookId) {
		
		this.bookName = bookName;
		this.issueDate = issueDate;
		this.userId = userId;
		this.bookId = bookId;
	}
    
	public int getBookId() {
		return bookId;
	}
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
    
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDate issueDate) {
		this.issueDate = issueDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
