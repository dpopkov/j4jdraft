package ru.j4jdraft.mt.nonblockingcache;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCache {
    private final ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    public void add(Base model) {
        Base oldModel = map.putIfAbsent(model.getId(), model);
        if (oldModel != null) {
            throw new IllegalStateException("This model is present in the cache, use update");
        }
    }

    public void update(Base model) {
        Base result = map.computeIfPresent(model.getId(), (key, oldModel) -> {
            if (oldModel.getVersion() != model.getVersion()) {
                throw new OptimisticException(
                        "An attempt to use stale model that leads to overwriting data");
            }
            model.updateVersion();
            return model;
        });
        if (result == null) {
            throw new IllegalStateException("This model is absent in the cache, use add");
        }
    }

    public void delete(Base model) {
        map.remove(model.getId());
    }

    public Base getBy(int id) {
        return map.get(id);
    }
}
