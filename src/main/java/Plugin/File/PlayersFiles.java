package Plugin.File;

import Plugin.Listeners.PlayerListener;
import Plugin.xBxTcore;

import java.io.File;
import java.util.ArrayList;

public class PlayersFiles {
    protected xBxTcore plugin;
    protected String folderName;
    protected ArrayList<PlayerFile> configFiles;

    public PlayersFiles(xBxTcore plugin, String folderName){
        this.plugin = plugin;
        this.folderName = folderName;
        this.configFiles = new ArrayList<>();
        configure();
    }

    public void configure() {
        createFolder();
        reloadConfigs();
    }

    public void reloadConfigs(){
        this.configFiles = new ArrayList<>();
        registerConfigFiles();
        //loadConfigs();
    }

    public void createFolder(){
        File folder;
        try {
            folder = new File(plugin.getDataFolder() + File.separator + folderName);
            if(!folder.exists()){
                folder.mkdirs();
            }
        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    public void saveConfigFiles() {
        for (PlayerFile configFile : configFiles) {
            configFile.saveConfig();
        }
    }

    public void registerConfigFiles(){
        String path = plugin.getDataFolder() + File.separator + folderName;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            if (file.isFile()) {
                registerConfigFile(file.getName());
            }
        }
    }

    public ArrayList<PlayerFile> getConfigFiles() {
        return configFiles;
    }

    public PlayerFile getConfigFile(String pathName) {
        for (PlayerFile configFile : configFiles) {
            if (configFile.getPath().equals(pathName)) {
                return configFile;
            }
        }
        return null;
    }

    public PlayerFile registerConfigFile(String pathName) {
        PlayerFile config = new PlayerFile(pathName, folderName, plugin, true);
        config.registerConfig();
        configFiles.add(config);
        return config;
    }


    //public abstract void loadConfigs();

    //public abstract void saveConfigs();
}