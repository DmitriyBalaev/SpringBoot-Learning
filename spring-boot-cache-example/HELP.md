# Spring Cache
* Для Spring Boot нужна зависимость


    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
        <version>2.4.0</version>
    </dependency>

* Что бы включить кэширование

       @EnableCaching

* Что бы включить кэширование для метода достаточно поставить анатацию @Cacheable над методом,  а в параметрах указать имя кэша, в котором будут храниться результаты.

       @Cacheable("addresses")
       public String getAddress(Customer customer) {...}

* Удаление из кеша производиться посредством аннтотации @CacheEvict
        
        @CacheEvict(value="addresses", allEntries=true)
        public String getAddress(Customer customer) {...}
* Обновление кэша с помощью аннтотации @CachePut

        @CachePut(value="addresses")
        public String getAddress(Customer customer) {...}
        
    
