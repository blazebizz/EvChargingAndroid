package com.blaze.data.onboarding.repositories

import com.blaze.data.onboarding.apiservice.OnBoardingApiService
import javax.inject.Inject

class OnBoardingRepoImpl @Inject constructor(
    private val apiService: OnBoardingApiService
) : OnBoardingRepo