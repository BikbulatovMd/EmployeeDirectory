package sk.ukf.EmployeeDirectory.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private T data;
    private String message;
    private LocalDateTime datetime;

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
        this.datetime = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null, message);
    }

    public T getData() { return data; }
    public String getMessage() { return message; }
    public LocalDateTime getDatetime() { return datetime; }

    public void setData(T data) { this.data = data; }
    public void setMessage(String message) { this.message = message; }
    public void setDatetime(LocalDateTime datetime) { this.datetime = datetime; }
}

