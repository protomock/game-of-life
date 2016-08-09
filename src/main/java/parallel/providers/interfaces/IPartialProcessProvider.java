package parallel.providers.interfaces;

import parallel.IPartialProcess;

public interface IPartialProcessProvider {
    IPartialProcess createPartialProcess(String name);
}
