

I found spring boot to be simplest server app.
My app is based on this template project https://github.com/spring-guides/gs-spring-boot
I will use junit for unit tests, and spring for integration and acceptance tests.

CountableProduct - I want to have some realistic product representation in my basket, not map.

StorageController - to get inventory

H2 Database is based on this example http://mycuteblog.com/h2-database-example-hibernate-spring-boot/

InitializingBean - initial data in database https://stackoverflow.com/questions/38040572/spring-boot-loading-initial-data

<--Architecture of discounts system-->

ProductInPromotion - the object contains all information about the product, covered by any form of promotion: id, Product, amount (at which the promotion will apply), discountValue (from 0-1, which means % of discount)

the ProductInPromotion price is calculated based on the formula
discountPrice = price * (1-discountValue);

Discount - the object contains a list of products constituting a joint promotion.

This design of the discount system allows to grant a discount in any form. Among others:
10 apples 10% cheaper (Product "Apple", amount 10, discountValute 0.1)
20 apples 20% cheaper (Product "Apple", amount 20, discountValute 0.2) - which is different from 2x (10 apples 10% cheaper)!
3 apples at the normal price + 1 apple for free (Product "Apple", amount 3, discountValute 0; Product "Apple", amount 1, discountValute 1)
5 apples at the normal price + 1 banana 50% cheaper (Product "Apple", amount 5, discountValute 0; Product "Banana", amount 1, discountValute 0.5)
1 apple + 1 banana + orange with 30% discount (Product "Apple", amount 1, discountValute 0; Product "Banana", amount 1, discountValute 0; Product "Orange", amount 1, discountValute 0.3)
etc.

<------>

criteria queries - to find ProductInPromotion with selected criteria. Based on this example - https://chlebik.wordpress.com/2014/03/12/dajcie-kawalek-sqla-zapytania-criteria-api/
