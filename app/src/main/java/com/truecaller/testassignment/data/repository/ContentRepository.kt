package com.truecaller.testassignment.data.repository

import com.truecaller.testassignment.data.repository.contentDataSource.ContentDataSource
import javax.inject.Inject

class ContentRepository @Inject constructor(
    remoteDataSource: ContentDataSource
) {

    val remote = remoteDataSource
}