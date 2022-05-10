## Propose

This project was based on a previous project on my github and on my personal project that was used
as a base

## Configuration

add a Github Oauth key to your gradle.properties

```
github_access_token = <YOUR KEY HERE>
```

## Changes needed

- Enable Configuration Change support
   the app do not allow configuration change (rotation, memory, etc), would be great to give support,
   so the user will not lose the location he is seeing or interacting

- Add hilt/anvil (specification said to use dagger directly)
    The app uses dagger2 only, to make the development easier we could use anvil or hilt

- Module for shared preferences
    domains::profile method should be a pure jvm module, but right know is a android module due to
    sharedPreferences, we could create a infra module where a wrapper for sharedPreferences is created,
    this way all modules that want to use sharedPreferences would be pure jvm if they can

- Remove Resource reference from FetchProfileDataUseCase
    We could create a proper abstraction for resources usage, this way no domain layer would knew
    about a android class like `resource` and also would be possible to get resource data
