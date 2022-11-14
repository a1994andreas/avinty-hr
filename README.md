Approximate Time Per Task
1. Project setup ~ 10 m
2. Initial DB with liquibase ~ 30 minutes
3. APIs And Project structure (Initial Controllers, Services, Entities etc) ~ 2 hours
4. CORS ~ 15 minutes
5. Liquibase new column ~ 10 minutes
6. Custom Exception handling (Checks on unique employee email, unique department name etc.) ~ 30 minutes
7. Spring Security ~ 1.5 hour

Additional Notes
1. I have also created CustomUserDetails and AuditorResolver in order to populate correctly the created_by updated_by
   columns using the @CreatedBy and @LastModifiedBy annotations
2. I have also added a couple of unit tests for the EmployeeController
