package com.chargify.exceptions;

import java.util.Collection;

public class ChargifyException extends RuntimeException
{
  ChargifyException( String errorMessage )
  {
    super( errorMessage );
  }

  private ChargifyException( Collection errorMessages )
  {
    super( String.join( " && ", errorMessages.toString() ) );
  }

  static ChargifyException fromErrors( Collection errorMessages )
  {
    if( errorMessages.size() == 1 )
    {
      return fromError( errorMessages.iterator().next().toString() );
    }
    else
    {
      return new ChargifyException( errorMessages );
    }
  }

  private static ChargifyException fromError( String errorMessage )
  {
    switch( errorMessage )
    {
      case MissingNameException.MESSAGE:
        return new MissingNameException();
      case ApiHandleNotUniqueException.MESSAGE:
        return new ApiHandleNotUniqueException();
      default:
        return new ChargifyException( errorMessage );
    }
  }
}
