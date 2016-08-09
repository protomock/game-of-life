package parallel.providers;

import parallel.providers.interfaces.IProcessorInformationProvider;

public class ProcessorInformationProvider implements IProcessorInformationProvider {
    @Override
    public int getNumberOfProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }
}
