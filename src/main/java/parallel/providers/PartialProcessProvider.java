package parallel.providers;


import parallel.IPartialProcess;
import parallel.providers.interfaces.IPartialProcessProvider;
import parallel.PartialProcess;
import parallel.evaluators.interfaces.ILifeEvaluator;

public class PartialProcessProvider implements IPartialProcessProvider {
    private ILifeEvaluator lifeEvaluator;

    public PartialProcessProvider(ILifeEvaluator lifeEvaluator) {
        this.lifeEvaluator = lifeEvaluator;
    }

    @Override
    public IPartialProcess createPartialProcess(String name) {
        return new PartialProcess(name, lifeEvaluator);
    }
}
