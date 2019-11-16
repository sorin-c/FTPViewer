package org.ftpviewer.fileexplorer;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

public abstract class FileExplorerFx implements FileExplorer {

    static File CurrDirFile;
    static String CurrDirStr;
    static Label lbl;
    static String CurrDirName;
    static TilePane tilePane;
    SimpleDateFormat sdf;

    TableView<FileInfo> tableview;
    TableColumn<FileInfo, ImageView> image;
    TableColumn<FileInfo, String> date;
    TableColumn<FileInfo, String> name;
    TableColumn<FileInfo, String> size;

    FileExplorerFx() {

    }

    @Override
    public Image getIconImageFX(File f) {
        ImageIcon icon = (ImageIcon) FileSystemView.getFileSystemView().getSystemIcon(f);
        java.awt.Image img = icon.getImage();
        BufferedImage bimg = (BufferedImage) img;
        Image imgfx = SwingFXUtils.toFXImage(bimg,null);
        return imgfx;
    }

    @Override
    public TreeItem<String>[] TreeCreate(File dir) {
        return new TreeItem[0];
    }

    @Override
    public String calculateSize(File f) {
        return null;
    }

    @Override
    public String FindAbsolutePath(TreeItem<String> item, String s) {
        return null;
    }

    @Override
    public boolean IsDrive(File f) {
        return false;
    }

    @Override
    public int FilesHiddensCount(File dir) {
        return 0;
    }

    @Override
    public void CreateTreeView(TreeView<String> treeview) {

    }

    @Override
    public void CreateTableView(TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image, TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name, TableColumn<FileInfo, String> size) {

    }

    @Override
    public void CreateTableView() {

    }

    @Override
    public void CreateTilesView() {

    }

    @Override
    public void setLabelTxt() {

    }

    @Override
    public void Initiate() {

    }

    @Override
    public void setValues(TableView<FileInfo> tableview, TableColumn<FileInfo, ImageView> image, TableColumn<FileInfo, String> date, TableColumn<FileInfo, String> name, TableColumn<FileInfo, String> size) {

    }

    @Override
    public void CreateTiles() {

    }
}
