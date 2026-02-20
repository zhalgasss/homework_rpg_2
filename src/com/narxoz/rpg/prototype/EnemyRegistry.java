package com.narxoz.rpg.prototype;

import com.narxoz.rpg.enemy.Enemy;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EnemyRegistry {

    private final Map<String, Enemy> templates = new HashMap<>();

    // register base template
    public void registerTemplate(String key, Enemy enemy) {
        templates.put(key, enemy);
    }


    public Enemy createFromTemplate(String key) {
        Enemy template = templates.get(key);
        if (template == null) {
            throw new IllegalArgumentException("No enemy template found for key: " + key);
        }
        return template.clone();
    }

    public Set<String> listTemplates() {
        return templates.keySet();
    }
}