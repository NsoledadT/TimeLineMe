package timeLine.Me.persistence;

public class PersistenceException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenceException() {
		super();
	}

	public PersistenceException(String msg) {
        super(msg);
    }

	public PersistenceException(Exception exception) {
		super(exception);
	}

}
