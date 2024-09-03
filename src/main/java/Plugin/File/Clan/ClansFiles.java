package Plugin.File.Clan;

import Plugin.xBxTcore;

import java.io.File;
import java.util.ArrayList;

public class ClansFiles {
    protected xBxTcore plugin;
    protected String folderName;
    protected ArrayList<ClanFile> configFiles;

    public ClansFiles(xBxTcore plugin, String folderName){
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
        for (ClanFile configFile : configFiles) {
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

    public ArrayList<ClanFile> getConfigFiles() {
        return configFiles;
    }

    public ClanFile getConfigFile(String pathName) {
        for (ClanFile configFile : configFiles) {
            if (configFile.getPath().equals(pathName)) {
                return configFile;
            }
        }
        return null;
    }

    public ClanFile registerConfigFile(String pathName) {
        ClanFile config = new ClanFile(pathName, folderName, plugin, true);
        config.registerConfig();
        configFiles.add(config);
        return config;
    }


    //public abstract void loadConfigs();

    //public abstract void saveConfigs();
}