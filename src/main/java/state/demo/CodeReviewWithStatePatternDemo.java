package state.demo;

/**
 * @Author Yunxiang He
 */
public class CodeReviewWithStatePatternDemo {

    interface State {
        void createNewReview();

        void createNewRevision();

        void sendOutForReview();

        void commentOnTheReview();

        void approveTheReview();

        void closeTheReview();
    }

}

