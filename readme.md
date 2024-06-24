# Countries list app

### Functional Requirements
- [x] Fetch a list of countries in JSON format
- [x] Display all the countries in a ***RecyclerView*** ordered by the position they appear in the JSON
- [x] Each table cell show the country's "name", "region", "code" and "capital" in this format:
```
  --------------------------------- 
  | "name", "region"              | 
  |                       "code"  | 
  | "capital"                     | 
  ---------------------------------
```
- [x] The user should be able to scroll thru the entire list of countries
- The implementation should be robust
  - [x] handle errors
  - [x] edge cases
  - [x] support device rotation

### Non Functional Requirements
- [x] Use coroutines
- [x] Do not use Compose 
- [x] Do not use Dagger 
- [x] No deps injections

### Nice to have
- [x] Fragment
- [x] UseCase
- [x] RecyclerView
- [x] Domain layer and Mapper
- [x] Manual DI
- [x] DiffUtils
- [x] Network error handling

### What features I've developed in this project?
- An Android application that uses Clean Architecture with **MVVM** and **UseCases**
  - Domain abstraction layer (Repository, Model, Use Cases, ... )
  - Data interaction layer (API, DTO, ... )
  - ViewModel business logic layer (Dispatching ***Intent*** behaviours for cleaner V-VM interaction)
  - View (Activity and Fragment)
- Handles a manual dependency injection implementation that ensures beans whether they are needed. Enclosed in _Modules_ for cleaner code organization.
- Coroutines usage and dispatcher awarenes for networking calls
- ViewModel creation through _ViewModelProviderFactory_ and generalized for a complex business level project.
- Reusable business login in ***UseCases*** for ease of maintanability and testing and code reuse
- Abstraction layer ***Resource*** for the result of each potential repository result
  - Handling abstract _Success_ and _Error_ responces  
- Activity and Fragment UI segmentation. No ComposeUI. Ensuring **configuration changes** awareness.
- **RecyclerView** for drawing the list datas with a proper performance attention in data changes using _DiffUtils_
- _NetwokingHelper_, a clean utils for checking the connectivity state of the device
- Mapper for converting the DTO into Model
- 

### What is not here but I'd love to have
- Logging system
- Network interceptor for HTTPS statuses
- AUTH interceptor for ensuring authentication in HTTP calls
- Security layers build on top of:
  - SSLPinning
  - code obfuscation via R8 and String sectrets obfuscator
  - Encrypted local storage (for auth tokens, ... )
- Event analytics and crashlytics
- More structured tests
- An app Icon :) 
- Each needed feature for the project that is big or crucial or production level.
