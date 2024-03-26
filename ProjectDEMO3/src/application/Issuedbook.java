package application;

import java.time.LocalDate;

public class Issuedbook {
	private String bookName = null;
	private LocalDate issueDate = null;
    private int userId = 0;
    
    public Issuedbook(String bookName, LocalDate issueDate, int userId) {
		
		this.bookName = bookName;
		this.issueDate = issueDate;
		this.userId = userId;
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
