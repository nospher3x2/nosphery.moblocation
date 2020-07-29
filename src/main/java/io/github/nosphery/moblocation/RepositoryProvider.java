package io.github.nosphery.moblocation;

import io.github.nosphery.moblocation.mob.storage.MobRepository;

/**
 * @author oNospher
 **/
public class RepositoryProvider {

    private static MobRepository mobRepository;

    public RepositoryProvider() {
        mobRepository = new MobRepository();
        mobRepository.createTable();
    }

    public static MobRepository getMobRepository() {
        return mobRepository;
    }

}
