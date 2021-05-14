package imatmini;

public interface PurchaseSteps extends Info {
    public void next();
    public void back();

    public void addNextStep(PurchaseSteps nextStep);
    public void addPreviousStep(PurchaseSteps previousStep);
}
