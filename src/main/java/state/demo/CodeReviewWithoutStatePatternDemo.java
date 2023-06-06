package state.demo;

/**
 * https://www.notion.so/yunxianghe/State-6814f6151df54ead9c2817c555abbe1a?pvs=4
 *
 * @Author Yunxiang He
 */
public class CodeReviewWithoutStatePatternDemo {

    public static void main(String[] args) {
        CodeReview cr = new CodeReview();
        System.out.println(cr.currentState);
        cr.createNewReview();
        cr.sendOutForReview();
        cr.commentOnTheReview();
        cr.createNewRevision();
        cr.sendOutForReview();
        cr.approveTheReview();
        cr.closeTheReview();
    }

    enum State {
        NULL,
        DRAFT,
        REVIEW,
        COMMENTED,
        APPROVED,
        CLOSED, // New added state later
    }

    public static class CodeReview {

        private State currentState;

        public CodeReview() {
            currentState = State.NULL;
        }

        public void createNewReview() {
            String methodName = "createNewReview()";
            switch (currentState) {
                case NULL:
                    System.out.println("Creating new review");
                    currentState = State.DRAFT;
                    break;
                case DRAFT:
                case REVIEW:
                case COMMENTED:
                case APPROVED:
                case CLOSED: // new Added transition
                    System.err.println(errorMsg(methodName, currentState));
                    break;
            }
        }

        public void createNewRevision() {
            String methodName = "createNewRevision()";
            switch (currentState) {
                case REVIEW:
                case COMMENTED:
                    System.out.println("Creating new review");
                    currentState = State.DRAFT;
                    break;
                case NULL:
                case DRAFT:
                case APPROVED:
                case CLOSED: // new Added transition
                    System.err.println(errorMsg(methodName, currentState));
                    break;
            }
        }

        public void sendOutForReview() {
            String methodName = "sendOutForReview()";
            switch (currentState) {
                case DRAFT:
                    System.out.println("Sending out for the review");
                    currentState = State.REVIEW;
                    break;
                case NULL:
                case REVIEW:
                case COMMENTED:
                case APPROVED:
                case CLOSED: // new Added transition
                    System.err.println(errorMsg(methodName, currentState));
                    break;
            }
        }

        public void commentOnTheReview() {
            String methodName = "commentOnTheReview()";
            switch (currentState) {
                case REVIEW:
                    System.out.println("Commenting the review");
                    currentState = State.COMMENTED;
                    break;
                case NULL:
                case DRAFT:
                case COMMENTED:
                case APPROVED:
                case CLOSED: // new Added transition
                    System.err.println(errorMsg(methodName, currentState));
                    break;
            }
        }

        public void approveTheReview() {
            String methodName = "approveTheReview()";
            switch (currentState) {
                case REVIEW:
                    System.out.println("Approving the review");
                    currentState = State.APPROVED;
                    break;
                case NULL:
                case DRAFT:
                case APPROVED:
                case COMMENTED:
                case CLOSED: // new Added transition
                    System.err.println(errorMsg(methodName, currentState));
                    break;
            }
        }

        // new Added transition
        public void closeTheReview() {
            String methodName = "approveTheReview()";
            switch (currentState) {
                case DRAFT:
                case REVIEW:
                case COMMENTED:
                case APPROVED:
                    System.out.println("Closing the review");
                    currentState = State.CLOSED;
                    break;
                case NULL:
                    System.err.println(errorMsg(methodName, currentState));
                    break;
            }
        }
    }

    private static String errorMsg(String methodName, State state) {
        return String.format("Can't %s when the state is %s", methodName, state);
    }

}

