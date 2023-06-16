package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Image image = new Image();

        image.setDescription(description);
        image.setDimensions(dimensions);

        Blog blog = blogRepository2.findById(blogId).get();

        image.setBlog(blog);
        blog.getImageList().add(image);




        blogRepository2.save(blog);

        return image;


    }

    public void deleteImage(Integer id){


        imageRepository2.deleteById(id);


    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`

          Image image= imageRepository2.findById(id).get();

          String totalArea[] = screenDimensions.split("X");
          String givenImageArea[] = image.getDimensions().split("X");

        int l = Integer.valueOf(totalArea[0]) / Integer.valueOf(givenImageArea[0]);
        int b = Integer.valueOf(totalArea[1]) / Integer.valueOf(givenImageArea[1]);

        return l * b;
    }
}
