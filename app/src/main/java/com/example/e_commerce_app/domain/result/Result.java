package com.example.e_commerce_app.domain.result;

public abstract class Result<R> {
    private Result() {
    }

    public static final class Success<T> extends Result<T> {
        private final T data;

        public Success(T data) {
            super();
            this.data = data;
        }

        public T getData() {
            return data;
        }
    }

    public static final class Error extends Result<Throwable> {
        private final Exception value;

        public Error(Exception value) {
            super();
            this.value = value;
        }

        public Exception getValue() {
            return value;
        }
    }
}
