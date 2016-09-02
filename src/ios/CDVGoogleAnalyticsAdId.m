#import "CDVGoogleAnalyticsAdId.h"
#import <AdSupport/ASIdentifierManager.h>

@interface CDVGoogleAnalyticsAdId()

- (int) _getAdId: (NSString**)aid;

@end

@implementation CDVGoogleAnalyticsAdId

- (void) getAdId: (CDVInvokedUrlCommand*)command {
    [self.commandDelegate runInBackground:^{
        NSString *result = nil;
        CDVPluginResult* pluginResult = nil;
        
        if ([self _getAdId:&result] == 0) {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK
                                             messageAsString:result];
        } else {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR
                                             messageAsString:result];
        }
        
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (int) _getAdId: (NSString**)aid {
    NSString *uuid = [[[ASIdentifierManager sharedManager] advertisingIdentifier] UUIDString];
    if (uuid) {
        *aid = uuid;
        return 0;
    }
    else {
        *aid = @"No Advertising Identifier for device";
        return -1;
    }
}

@end
