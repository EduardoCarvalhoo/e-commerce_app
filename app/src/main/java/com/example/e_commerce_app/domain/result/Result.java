package com.example.e_commerce_app.domain.result;

public abstract class Result<T> {
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

    public static final class Error<T> extends Result<T> {
        private final Throwable value;

        public Error(Throwable value) {
            super();
            this.value = value;
        }

        public Throwable getValue() {
            return value;
        }
    }
}
