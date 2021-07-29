/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dal.ImageDAO;

/**
 *
 * @author DELL
 */
public class Gallery {
    private int id;
    private String title;
    private String galleryContent;
    private String galleryName;
    private String firstImg;

    public Gallery() {
    }

    public Gallery(int id, String title, String galleryContent, String galleryName) {
        this.id = id;
        this.title = title;
        this.galleryContent = galleryContent;
        this.galleryName = galleryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGalleryContent() {
        return galleryContent;
    }

    public void setGalleryContent(String galleryContent) {
        this.galleryContent = galleryContent;
    }

    public String getGalleryName() {
        return galleryName;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }

    public String getFirstImg() throws Exception {
        ImageDAO imageDAO = new ImageDAO();
        return imageDAO.get1stImageByGalleryID(id);
    }

    public void setFirstImg(String firstImg) {
        this.firstImg = firstImg;
    }
    
}
