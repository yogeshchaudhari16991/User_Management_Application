package util;

/**
 * Created by yoges on 7/9/2016.
 */
public class ErrorClass {

        private String message;

        public ErrorClass(String message) {

            this.message = message;

        }

        public ErrorClass(Exception e) {

            this.message = e.getMessage();

        }

        public ErrorClass(Error e) {
            this.message = e.getMessage();
        }

        public String getMessage() {

            return this.message;

        }
}
