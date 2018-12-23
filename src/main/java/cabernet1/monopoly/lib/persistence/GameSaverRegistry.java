package cabernet1.monopoly.lib.persistence;

import cabernet1.monopoly.domain.Game;
import cabernet1.monopoly.domain.game.board.Board;
import cabernet1.monopoly.domain.game.bot.BotStrategyFactory;
import cabernet1.monopoly.domain.game.die.cup.JailDiceCup;
import cabernet1.monopoly.domain.game.die.cup.NormalDiceCup;
import cabernet1.monopoly.domain.game.die.cup.RollThreeDiceCup;
import cabernet1.monopoly.domain.game.player.PlayerFactory;
import cabernet1.monopoly.utils.RepresentationInvariant;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Set;

public class GameSaverRegistry implements RepresentationInvariant {
    public static final String INSTANCE_VAR_NAME = "_instance";
    private static GameSaverRegistry _instance = null;
    private final HashMap<String, Class<? extends Serializable>> saveableClasses = new HashMap<>();

    private GameSaverRegistry() {
        saveableClasses.put("game", Game.class);
        saveableClasses.put("botStrategyFactory", BotStrategyFactory.class);
        saveableClasses.put("jailDiceCup", JailDiceCup.class);
        saveableClasses.put("rollThreeDiceCup", RollThreeDiceCup.class);
        saveableClasses.put("normalDiceCup", NormalDiceCup.class);
        saveableClasses.put("board", Board.class);
        saveableClasses.put("playerFactory", PlayerFactory.class);
    }

    public static synchronized GameSaverRegistry getInstance() {
        if (_instance == null) {
            _instance = new GameSaverRegistry();
        }
        return _instance;
    }

    public HashMap<String, Class<? extends Serializable>> getAllSaveableClasses() {
        return saveableClasses;
    }

    public Set<String> getSaveableClassNames() {
        return saveableClasses.keySet();
    }

    public Class<? extends Serializable> getSaveableClass(String name) {
        return saveableClasses.get(name);
    }

    @SuppressWarnings("unchecked")
    public <T extends Serializable> T getClassInstance(String name) throws NoSuchFieldException, IllegalAccessException {
        final Class<T> clazz = getSaveableClassByType(name);
        final Field instanceField = getSingletonInstanceField(clazz);
        return (T) instanceField.get(null);
    }

    public void setClassInstance(String name, Serializable instance) throws NoSuchFieldException, IllegalAccessException {
        final Class<? extends Serializable> clazz = getSaveableClass(name);
        final Field instanceField = getSingletonInstanceField(clazz);
        instanceField.set(null, instance);
    }

    @SuppressWarnings("unchecked")
    public <T extends Serializable> Class<T> getSaveableClassByType(String name) {
        return (Class<T>) getSaveableClass(name);
    }


    @Override
    public boolean repOK() {
        // Return whether all saveable class are marked so
        return saveableClasses.values().stream()
                .allMatch(clazz -> clazz.getDeclaredAnnotation(Saveable.class) != null && isSingleton(clazz));
    }

    @Override
    public String toString() {
        return "GameSaverRegistry{ " +
                "saveableClasses: " + saveableClasses +
                " }";
    }

    private Field getSingletonInstanceField(Class<? extends Serializable> clazz) throws NoSuchFieldException {
        final Field instanceField = clazz.getDeclaredField(INSTANCE_VAR_NAME);
        if (!Modifier.isStatic(instanceField.getModifiers())) {
            throw new NoSuchFieldException();
        }
        instanceField.setAccessible(true);
        return instanceField;
    }

    private static boolean isSingleton(Class<?> clazz) {
        try {
            final Field instanceField = clazz.getDeclaredField(INSTANCE_VAR_NAME);
            if (instanceField == null) {
                return false;
            }
            if (!Modifier.isStatic(instanceField.getModifiers())) {
                return false;
            }
            return clazz.getDeclaredConstructor() != null;
        } catch (NoSuchFieldException | NoSuchMethodException e) {
            return false;
        }
    }
}
