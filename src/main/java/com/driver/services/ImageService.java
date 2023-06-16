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

        blog.getImageList().add(image);
        image.setBlog(blog);

        blogRepository2.save(blog);

        return image;


    }

    public void deleteImage(Integer id){
        Image image = imageRepository2.findById(id).get();

        imageRepository2.delete(image);


    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
          int count = 0;
          Image image= imageRepository2.findById(id).get();

          String currDimension = image.getDimensions();
          String[] arr = currDimension.split("X");
          int curr = 1;

        for(String a : arr)
            curr *= Integer.valueOf(a);


        String[] arr2 = screenDimensions.split("X");
        int curr2 = 1;

        for(String a : arr2)
            curr2 *= Integer.valueOf(a);


        count = curr2 / curr;

          return count;
    }
}
