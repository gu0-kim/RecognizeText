# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/Gu/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}


#指定代码的压缩级别
-optimizationpasses 5

#包明不混合大小写
-dontusemixedcaseclassnames

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses

#优化  不优化输入的类文件
-dontoptimize

#预校验
-dontpreverify

#混淆时是否记录日志
-verbose

# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
-keepattributes *Annotation*
#如果引用了v4或者v7包
-dontwarn android.support.**
-keep class android.support.**{*;}
#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
native <methods>;
}
#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable
-keep class * implements java.io.Serializable{*;}
-keep class com.google.gson.** { *;}