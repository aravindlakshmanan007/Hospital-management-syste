package global.coda.hms.model;
/**
 *
 * @author VC
 *
 * @param <T> generic_Paramenter
 */
public class CustomResponse<T> {
	private int status;
	private T data;
	/**
	 *
	 * @return status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 *
	 * @param status status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 *
	 * @return data
	 */
	public T getData() {
		return data;
	}
	/**
	 *
	 * @param data data
	 */
	public void setData(T data) {
		this.data = data;
	}
	/**
	 *
	 */
	@Override
	public String toString() {
		return "response [status=" + status + ", data=" + data + "]";
	}
}
